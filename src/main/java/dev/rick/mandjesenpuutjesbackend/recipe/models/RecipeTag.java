package dev.rick.mandjesenpuutjesbackend.recipe.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class RecipeTag {

    private String tagName;
}
