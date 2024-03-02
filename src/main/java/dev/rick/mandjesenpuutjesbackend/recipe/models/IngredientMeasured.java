package dev.rick.mandjesenpuutjesbackend.recipe.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientMeasured {

    private int amount;
    private String unit;
    private String name;
}
