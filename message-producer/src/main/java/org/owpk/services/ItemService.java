package org.owpk.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.owpk.entities.Item;
import org.owpk.entities.dto.ItemDto;
import org.owpk.exception.ResourceNotFoundException;
import org.owpk.repositories.ItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CommonsLog
public class ItemService {
    private final ItemRepo itemRepo;


    public Item findById(Long id) {
        return itemRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("item with id: " + id));
    }

    public List<ItemDto> findAll() {
        return itemRepo.findAll().stream().map(ItemDto::new).collect(Collectors.toList());
    }
}
