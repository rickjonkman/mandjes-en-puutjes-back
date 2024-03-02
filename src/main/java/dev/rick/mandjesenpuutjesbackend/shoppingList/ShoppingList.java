package dev.rick.mandjesenpuutjesbackend.shoppingList;

import dev.rick.mandjesenpuutjesbackend.product.Product;
import dev.rick.mandjesenpuutjesbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "current")
    private boolean currentList;

    @Column(name = "created")
    private LocalDate creationDate;

    @ElementCollection
    private List<Grocery> groceries = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public void addGroceryToList(Grocery grocery) {
        this.groceries.add(grocery);
    }

}
