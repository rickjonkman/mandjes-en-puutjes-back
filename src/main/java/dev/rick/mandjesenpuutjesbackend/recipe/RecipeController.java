package dev.rick.mandjesenpuutjesbackend.recipe;

import dev.rick.mandjesenpuutjesbackend.recipe.dtos.RecipeFullOutputDTO;
import dev.rick.mandjesenpuutjesbackend.recipe.dtos.RecipeInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public ResponseEntity<RecipeFullOutputDTO> addNewRecipe(RecipeInputDTO inputDTO) {

    }
}
