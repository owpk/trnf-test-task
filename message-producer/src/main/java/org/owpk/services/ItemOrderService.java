package org.owpk.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owpk.entities.Order;
import org.owpk.entities.dto.OrderDto;
import org.owpk.repositories.ItemOrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemOrderService {
    private final ItemOrderRepo itemOrderRepo;

    public List<OrderDto> getAllOrderItemsDtosForUserWithId(Long id) {
        return itemOrderRepo.findOrdersForUser(id).stream()
                .map(OrderDto::new).collect(Collectors.toList());

    }

    public void save(Order order) {
        itemOrderRepo.save(order);
    }
}
