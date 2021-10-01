package org.owpk.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.owpk.entities.dto.ItemDto;
import org.owpk.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<ItemDto> getAll() {
        var items = itemService.findAll();
        log.info("REST ITEMS REQUESTED :: {}", items);
        return items;
    }
}
