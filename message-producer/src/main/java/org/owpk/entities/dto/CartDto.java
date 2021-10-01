package org.owpk.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.owpk.entities.Item;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {
    @JsonProperty(value = "items")
    private List<ItemDto> items;

    @JsonProperty(value = "price")
    private Integer price;

    public CartDto(List<Item> items, Integer price) {
        this.items = items.stream().map(ItemDto::new).collect(Collectors.toList());
        this.price = price;
    }
}
