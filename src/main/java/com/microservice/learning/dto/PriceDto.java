package com.microservice.learning.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PriceDto implements Serializable {
    private String codeProduct;
    private double price;


    @Override
    public String toString() {

        return "PriceDto{" + "codProduct='" + codeProduct + '\'' + ", price=" + price + '}';
    }
}
