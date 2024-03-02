package dev.rick.mandjesenpuutjesbackend.user;

import jakarta.persistence.*;
import lombok.*;

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
    private Set<Authority> authorities;

//    @OneToMany
//    private List<ShoppingList> shoppingLists;


    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}
