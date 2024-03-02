package dev.rick.mandjesenpuutjesbackend.shoppingList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    @Query("SELECT s FROM ShoppingList s WHERE s.user.username = :username ORDER BY s.id DESC LIMIT 5")
    Optional<List<ShoppingList>> findShoppingListsByUser_Username(String username);


    @Query("SELECT s FROM ShoppingList s WHERE s.currentList = true")
    Optional<ShoppingList> findShoppingListsByCurrentListAndUser_Username(boolean isCurrentList, String username);
}
