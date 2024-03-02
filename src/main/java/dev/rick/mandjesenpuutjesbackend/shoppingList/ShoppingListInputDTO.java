package dev.rick.mandjesenpuutjesbackend.shoppingList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListInputDTO {

    private LocalDate creationDate;
    private String[] groceries;
    private String username;
}
