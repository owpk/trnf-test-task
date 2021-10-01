package org.owpk.entities.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.owpk.entities.Item;

@NoArgsConstructor
@Getter
@Setter
@JsonAutoDetect
@ToString
public class ItemDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "cost")
    private Integer cost;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.cost = item.getCost();
    }
}
