package org.scaler.shopnest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DummyJsonProductDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String thumbnail;
}
