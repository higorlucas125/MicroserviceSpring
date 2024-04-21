package com.microservice.learning.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StockDto implements Serializable{
    private String codeProduct;
    private int amount;


    @Override
    public String toString() {

        return "StockDto{" + "codeProduct='" + codeProduct + '\'' + ", amount=" + amount + '}';
    }
}
