package org.owpk.utils;

import lombok.RequiredArgsConstructor;
import org.owpk.entities.OrderItem;
import org.owpk.entities.dto.CartDto;
import org.owpk.services.ItemService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartManager {

    private final ItemService itemService;

    private List<OrderItem> itemList;
    private Integer totalCost;

    @PostConstruct
    public void init() {
        itemList = new ArrayList<>();
    }

    public void addItem(Long id) {
        var itemFromDB = itemService.findById(id);
        itemList.add(new OrderItem(itemFromDB));
        recalculate();
    }

    private void recalculate() {
        totalCost = itemList.stream()
                .map(x -> x.getItem().getCost())
                .reduce(0, Integer::sum);
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void clear() {
        itemList.clear();
        recalculate();
    }

    public CartDto createCartDto() {
        var list = itemList.stream()
                .map(OrderItem::getItem)
                .collect(Collectors.toList());
        return new CartDto(list, totalCost);
    }

    public void removeItem(Long id) {
        for (int i = 0; i < itemList.size(); i++)
            if (itemList.get(i).getItem().getId().equals(id)) {
                itemList.remove(i);
                break;
            }
        recalculate();
    }

}
