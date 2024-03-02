package dev.rick.mandjesenpuutjesbackend.shoppingList;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shopping-lists")
@AllArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
}
