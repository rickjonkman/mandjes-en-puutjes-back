package dev.rick.mandjesenpuutjesbackend.recipe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeInstructionDTO {

    private int step;
    private String instructionText;

}
