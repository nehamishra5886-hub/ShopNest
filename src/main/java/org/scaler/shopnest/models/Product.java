package org.scaler.shopnest.models;


import lombok.Getter;
import lombok.Setter;
import org.scaler.shopnest.models.enums.Category;

import java.math.BigDecimal;

@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Category category;


}
