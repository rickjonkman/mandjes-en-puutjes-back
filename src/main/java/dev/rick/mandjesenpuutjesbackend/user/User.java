package dev.rick.mandjesenpuutjesbackend.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany()
    private Set<Authority> authorities;

//    @OneToMany
//    private List<ShoppingList> shoppingLists;
}
