package dev.rick.mandjesenpuutjesbackend.recipe;

import dev.rick.mandjesenpuutjesbackend.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
