package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.recipe.models.Recipe;
import dev.rick.mandjesenpuutjesbackend.shoppingList.ShoppingList;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String firstname;
    private boolean enabled;

    @Embedded
    private UserPreferences preferences;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Authority> authorities = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<ShoppingList> shoppingLists;

    @ManyToMany
    private List<Recipe> savedRecipes;

    @OneToMany(mappedBy = "createdByUser")
    private List<Recipe> createdRecipes;



    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
