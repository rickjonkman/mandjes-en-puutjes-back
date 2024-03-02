package dev.rick.mandjesenpuutjesbackend.recipe.models;

import dev.rick.mandjesenpuutjesbackend.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    private long recipeId;

    private String recipeName;

    private int servings;

    private int prepTime;

    private String imageFile;

    @ElementCollection
    @CollectionTable(
            name = "recipe_tags",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeTag> tags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "recipe_supplies",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private List<String> supplies = new ArrayList<>();

    @ManyToMany(mappedBy = "recipes")
    private List<IngredientName> ingredients = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "recipe_ingredients_measured",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private List<IngredientMeasured> ingredientsMeasured = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "recipe_instructions",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeInstruction> instructions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "creator")
    private User createdByUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipes_saved_by_users",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> savedByUsers = new ArrayList<>();
}
