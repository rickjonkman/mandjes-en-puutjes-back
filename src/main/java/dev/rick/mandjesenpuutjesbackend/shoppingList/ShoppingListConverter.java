package dev.rick.mandjesenpuutjesbackend.shoppingList;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListConverter {


    public List<ShoppingListOutputDTO> convertToListDTO(List<ShoppingList> shoppingLists) {
        List<ShoppingListOutputDTO> outputDTOList = new ArrayList<>();

        for (ShoppingList list : shoppingLists) {
            ShoppingListOutputDTO singleListDTO = convertToDTO(list);
            outputDTOList.add(singleListDTO);
        }

        return outputDTOList;
    }

    public ShoppingListOutputDTO convertToDTO(ShoppingList shoppingList) {
        ShoppingListOutputDTO outputDTO = new ShoppingListOutputDTO();
        outputDTO.setId(shoppingList.getId());
        outputDTO.setCreationDate(shoppingList.getCreationDate());
        outputDTO.setUsername(shoppingList.getUser().getUsername());
        outputDTO.setGroceries(convertToGroceryArray(shoppingList.getGroceries()));
        return outputDTO;
    }

    public List<Grocery> convertToGroceryList(String[] groceriesArray) {
        List<Grocery> groceryList = new ArrayList<>();

        for (String groceryName : groceriesArray) {
            Grocery newGrocery = convertToGrocery(groceryName);
            groceryList.add(newGrocery);
        }

        return groceryList;
    }

    public List<GroceryDTO> convertToGroceryDTOList(List<Grocery> groceries) {
        List<GroceryDTO> dtoList = new ArrayList<>();

        for (Grocery grocery : groceries) {
            GroceryDTO dto = convertToGroceryDTO(grocery);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public GroceryDTO convertToGroceryDTO(Grocery grocery) {
        GroceryDTO dto = new GroceryDTO();
        dto.setGroceryName(grocery.getGroceryName());
        return dto;
    }

    public Grocery convertDTOToGrocery(GroceryDTO groceryDTO) {
        Grocery newGrocery = new Grocery();
        newGrocery.setGroceryName(groceryDTO.getGroceryName());
        return newGrocery;
    }

    public Grocery convertToGrocery(String groceryName) {
        Grocery newGrocery = new Grocery();
        newGrocery.setGroceryName(groceryName);
        return newGrocery;
    }

    public String[] convertToGroceryArray(List<Grocery> groceries) {
        String[] groceryArray = new String[groceries.size()];
        groceries.toArray(new Grocery[0]);
        return groceryArray;
    }
}
