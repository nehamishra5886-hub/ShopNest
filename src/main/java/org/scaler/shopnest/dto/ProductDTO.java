package org.scaler.shopnest.dto;

import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private CategoryDTO category;
}
