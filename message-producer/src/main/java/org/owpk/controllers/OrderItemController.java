package org.owpk.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.owpk.common.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owpk.entities.Order;
import org.owpk.entities.dto.CartDto;
import org.owpk.entities.dto.OrderDto;
import org.owpk.services.ItemOrderService;
import org.owpk.services.RabbitMQSenderService;
import org.owpk.services.UserService;
import org.owpk.utils.CartManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderItemController {


    private final RabbitMQSenderService msgProducer;

    private final ItemOrderService itemOrderService;
    private final CartManager cartManager;
    private final UserService userService;

    @GetMapping(value = "/all/{user_id}", produces = "application/json")
    public List<OrderDto> getAllOrders(@PathVariable Long user_id) {
        var items = itemOrderService.getAllOrderItemsDtosForUserWithId(user_id);
        log.debug("items requested: " + items.toString());
        return items;
    }

    @GetMapping("/cart/add/{id}")
    public void addItemToCart(@PathVariable Long id) {
        log.debug("REST RESPONSE: /cart/add/{id} :: " + id);
        cartManager.addItem(id);
    }

    @GetMapping("/cart/remove/{id}")
    public void removeItemToCart(@PathVariable Long id) {
        log.debug("REST RESPONSE: /cart/remove/{id} :: " + id);
        cartManager.removeItem(id);
    }

    @GetMapping("/cart")
    public CartDto getCartContent() {
        return cartManager.createCartDto();
    }

    @PostMapping
    public void createOrder(@RequestParam Long id) throws JsonProcessingException {
        var user = userService.findById(id);
        var order = new Order(user, cartManager);
        itemOrderService.save(order);
        cartManager.clear();
        var msg = new Message();
        msg.setItemNames(order.getItems().stream().map(x -> x.getItem().getName()).collect(Collectors.toList()));
        msg.setTotalCost(order.getItems().stream().map(x -> x.getItem().getCost()).reduce(0, Integer::sum));
        msg.setUserEmail(order.getUser().getEmail());
        msgProducer.send(msg);
    }
}
