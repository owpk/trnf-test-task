package com.owpk.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Message {
    private String userEmail;
    private List<String> itemNames;
    private Integer totalCost;
}
