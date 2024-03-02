package dev.rick.mandjesenpuutjesbackend.recipe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeShortOutputDTO {

    private long recipeId;
    private String recipeName;
    private int servings;
    private int prepTime;
    private String imageFile;
    private List<TagDTO> tagDTOList = new ArrayList<>();
    private int savedByUsers;
}
