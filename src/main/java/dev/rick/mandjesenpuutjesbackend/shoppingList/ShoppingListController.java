package dev.rick.mandjesenpuutjesbackend.shoppingList;


import dev.rick.mandjesenpuutjesbackend.exceptions.NotAuthorizedException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/shopping-lists")
@AllArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping("/get-lists")
    public ResponseEntity<List<ShoppingListOutputDTO>> getShoppingLists(@RequestParam String username, Principal principal) {

        if (Objects.equals(principal.getName(), username)) {
            List<ShoppingListOutputDTO> shoppingListOutputDTOList = shoppingListService.getShoppingLists(username);
            return ResponseEntity.ok(shoppingListOutputDTOList);
        } else {
            throw new NotAuthorizedException();
        }
    }

    @GetMapping("/{userId}/get-new-list")
    public ResponseEntity<ShoppingListOutputDTO> getCurrentShoppingList(@PathVariable("userId") String username, Principal principal) {

        if (Objects.equals(principal.getName(), username)) {
            ShoppingListOutputDTO outputDTO = shoppingListService.getCurrentShoppingList(username);
            return ResponseEntity.ok(outputDTO);
        } else {
            throw new NotAuthorizedException();
        }

    }

    @PostMapping("/add-new")
    public ResponseEntity<ShoppingListOutputDTO> addNewShoppingList(@RequestParam String username, @RequestBody ShoppingListInputDTO inputDTO, Principal principal) {

        if (Objects.equals(principal.getName(), username)) {
            ShoppingListOutputDTO outputDTO = shoppingListService.addNewShoppingList(username, inputDTO);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/api/v1/shopping-lists/list/"+outputDTO.getId())
                    .toUriString());

            return ResponseEntity.created(uri).body(outputDTO);
        } else {
            throw new NotAuthorizedException();
        }
    }

    @PutMapping("/{id}/{userId}/add-grocery")
    public ResponseEntity<List<GroceryDTO>> addGroceryToList(
            @RequestBody GroceryDTO inputDTO,
            @RequestParam("userId") String username,
            @PathVariable("id") int id,
            Principal principal) {

        if (Objects.equals(principal.getName(), username)) {
            List<GroceryDTO> groceryList = shoppingListService.addGroceryToList(inputDTO, username, id);
            return ResponseEntity.ok(groceryList);
        } else {
            throw new NotAuthorizedException();
        }
    }
}
