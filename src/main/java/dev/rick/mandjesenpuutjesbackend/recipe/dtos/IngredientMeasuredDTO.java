package dev.rick.mandjesenpuutjesbackend.recipe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientMeasuredDTO {

    private int amount;
    private String unit;
    private String name;
}
