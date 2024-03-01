package dev.rick.mandjesenpuutjesbackend.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserPreferencesDTO {


    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;
}
