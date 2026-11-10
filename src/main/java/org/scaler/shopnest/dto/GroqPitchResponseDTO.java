package org.scaler.shopnest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroqPitchResponseDTO {

    private List<Choice> choices;
}
