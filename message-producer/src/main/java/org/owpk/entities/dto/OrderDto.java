package org.owpk.entities.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.owpk.entities.Item;
import org.owpk.entities.Order;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect
public class OrderDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "item_names")
    private String itemNames;

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "user_email")
    private String userEmail;

    @JsonProperty(value = "total_cost")
    private Integer totalCost;

    public OrderDto(Order itemOrder) {
        this.id = itemOrder.getId();
        this.itemNames = itemOrder.getItems().stream()
                .map(x -> x.getItem().getName())
                .collect(Collectors.joining(", "));
        this.userName = itemOrder.getUser().getName();
        this.userEmail = itemOrder.getUser().getEmail();
        this.totalCost = itemOrder.getItems().stream()
                .map(x -> x.getItem().getCost()).reduce(0, Integer::sum);
    }
}
