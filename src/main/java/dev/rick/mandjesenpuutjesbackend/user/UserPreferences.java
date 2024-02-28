package dev.rick.mandjesenpuutjesbackend.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserPreferences {

    @Column(name = "meat")
    private boolean showMeat;

    @Column(name = "fish")
    private boolean showFish;

    @Column(name = "vega")
    private boolean showVegetarian;

    @Column(name = "vegan")
    private boolean showVegan;
}
