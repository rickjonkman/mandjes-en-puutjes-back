package dev.rick.mandjesenpuutjesbackend.shoppingList;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepo;


}
