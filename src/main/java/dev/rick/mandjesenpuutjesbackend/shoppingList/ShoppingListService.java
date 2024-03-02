package dev.rick.mandjesenpuutjesbackend.shoppingList;

import dev.rick.mandjesenpuutjesbackend.exceptions.NothingFoundForUserException;
import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend.user.User;
import dev.rick.mandjesenpuutjesbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepo;
    private final UserRepository userRepo;
    private final ShoppingListConverter converter;

    public List<ShoppingListOutputDTO> getShoppingLists(String username) {
        List<ShoppingListOutputDTO> shoppingListOutputDTOList = new ArrayList<>();

        Optional<List<ShoppingList>> optionalLists = shoppingListRepo.findShoppingListsByUser_Username(username);
        if (optionalLists.isEmpty()) {
            throw new NothingFoundForUserException(username);
        } else {
            List<ShoppingList> foundLists = optionalLists.get();
            shoppingListOutputDTOList = converter.convertToListDTO(foundLists);
        }

        return shoppingListOutputDTOList;
    }

    public ShoppingListOutputDTO getCurrentShoppingList(String username) {
        ShoppingList foundShoppingList = findCurrentShoppingListFromUser(username);
        if (foundShoppingList != null) {
            return converter.convertToDTO(foundShoppingList);
        } else {
            throw new NothingFoundForUserException(username);
        }
    }

    public ShoppingListOutputDTO addNewShoppingList(String username, ShoppingListInputDTO inputDTO) {

        Optional<ShoppingList> optionalShoppingList = shoppingListRepo.findShoppingListsByCurrentListAndUser_Username(true, username);
        if (optionalShoppingList.isEmpty()) {
            throw new NothingFoundForUserException(username);
        } else {
            setCurrentListToFalse(optionalShoppingList.get());
            ShoppingList createdList = createNewShoppingList(inputDTO);

            return converter.convertToDTO(createdList);
        }
    }

    public List<GroceryDTO> addGroceryToList(GroceryDTO groceryDTO, String username, int id) {

        Optional<ShoppingList> optionalShoppingList = shoppingListRepo.findById(id);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException(id);
        } else {
            ShoppingList foundList = optionalShoppingList.get();
            foundList.addGroceryToList(converter.convertDTOToGrocery(groceryDTO));
            shoppingListRepo.save(foundList);

            return converter.convertToGroceryDTOList(foundList.getGroceries());
        }
    }

    public ShoppingList createNewShoppingList(ShoppingListInputDTO inputDTO) {
        ShoppingList newList = new ShoppingList();
        newList.setCurrentList(true);
        newList.setUser(findUserByUsername(inputDTO.getUsername()));
        newList.setCreationDate(inputDTO.getCreationDate());
        newList.setGroceries(converter.convertToGroceryList(inputDTO.getGroceries()));
        shoppingListRepo.save(newList);
        return newList;
    }

    public ShoppingList findCurrentShoppingListFromUser(String username) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepo.findShoppingListsByCurrentListAndUser_Username(true, username);
        return optionalShoppingList.orElse(null);
    }

    public void setCurrentListToFalse(ShoppingList list) {
        list.setCurrentList(false);
    }

    public void setCurrentListToTrue(ShoppingList list) {
        list.setCurrentList(true);
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException(username);
        } else {
            return optionalUser.get();
        }
    }
}
