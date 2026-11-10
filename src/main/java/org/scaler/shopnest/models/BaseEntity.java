package org.scaler.shopnest.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.scaler.shopnest.models.enums.State;

@Getter
@Setter

public class BaseEntity {

    private Long id;
    private String createdAt;
    private String lastUpdatedAt;
    @Enumerated(value = EnumType.STRING)
    private State state; // ACTIVE, INACTIVE

    
}
