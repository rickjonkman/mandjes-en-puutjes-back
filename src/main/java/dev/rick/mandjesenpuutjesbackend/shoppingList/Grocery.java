package dev.rick.mandjesenpuutjesbackend.shoppingList;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Grocery {

    private String groceryName;
}
