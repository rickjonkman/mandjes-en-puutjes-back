package dev.rick.mandjesenpuutjesbackend.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class UserConverter {

    private final PasswordEncoder encoder;

    public User convertToUser(UserInputDTO inputDTO) {
        User newUser = new User();
        newUser.setUsername(inputDTO.getUsername());
        newUser.setPassword(encoder.encode(inputDTO.getPassword()));
        newUser.setFirstname(inputDTO.getFirstname());
        newUser.setEnabled(inputDTO.isEnabled());
        return newUser;
    }

    public UserPreferencesDTO convertToUserPreferencesDTO(UserPreferences userPreferences) {
        return new UserPreferencesDTO(
                userPreferences.isShowMeat(),
                userPreferences.isShowFish(),
                userPreferences.isShowVegetarian(),
                userPreferences.isShowVegan()
        );
    }

    public UserOutputDTO convertUserToDTO(User user) {
        return new UserOutputDTO(
                user.getUsername(),
                user.getFirstname(),
                user.isEnabled(),
                convertAuthoritiesToArray(user.getAuthorities()));
    }

    public String[] convertAuthoritiesToArray(Set<Authority> authorities) {
        String[] authArray = new String[authorities.size()];

        List<String> authNames = new ArrayList<>();
        for (Authority authority : authorities) {
            String authName = authority.getAuthority();
            authNames.add(authName);
        }

        authNames.toArray(authArray);
        return authArray;
    }
}
