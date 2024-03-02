package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesbackend.exceptions.NotAuthorizedException;
import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserConverter converter;

    public UserRegisteredDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new NameIsTakenException(newUser.getUsername());
        } else {
            User createdUser = converter.convertToUser(newUser);
            userRepo.save(createdUser);
            return converter.convertToRegistrationDTO(createdUser);
        }
    }

    public UserOutputDTO getUserById(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser != null) {
            return converter.convertUserToDTO(foundUser);
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public UserPreferencesDTO getUserPreferencesById(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser != null) {
            return converter.convertToUserPreferencesDTO(foundUser.getPreferences());
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public UserPreferencesDTO changeUserPreferences(String username, UserPreferencesDTO inputDTO) {
        User foundUser = findUserByUsername(username);
        if (foundUser != null) {
            UserPreferences foundPreferences = foundUser.getPreferences();
            foundPreferences.setShowMeat(inputDTO.isShowMeat());
            foundPreferences.setShowFish(inputDTO.isShowFish());
            foundPreferences.setShowVegetarian(inputDTO.isShowVegetarian());
            foundPreferences.setShowVegan(inputDTO.isShowVegan());
            userRepo.save(foundUser);
            return converter.convertToUserPreferencesDTO(foundPreferences);
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public boolean addAdminAuthorityAsAdmin(Principal principal, String username) {

        boolean foundAdmin = doesAuthorityContainAdmin(principal.getName());
        if (foundAdmin) {
            User foundUser = findUserByUsername(username);
            if (foundUser != null) {
                foundUser.addAuthority(new Authority(username, "ADMIN"));
                return true;
            } else {
                throw new RecordNotFoundException(username);
            }
        } else {
            throw new NotAuthorizedException();
        }
    }

    public boolean deleteUserAsAdmin(Principal principal, String username) {

        boolean foundAdmin = doesAuthorityContainAdmin(principal.getName());
        if (foundAdmin) {
            User foundUser = findUserByUsername(username);
            if (foundUser != null) {
                userRepo.deleteById(username);
                return true;
            } else {
                throw new RecordNotFoundException(username);
            }
        } else {
            throw new NotAuthorizedException();
        }
    }

    public UserOutputDTO addAuthorityToRegisteredUser(AuthorityDTO authorityDTO) {
        User registeredUser = findUserByUsername(authorityDTO.getUsername());
        if (registeredUser != null) {
            Authority createdAuthority = converter.convertToAuthority(authorityDTO);
            registeredUser.addAuthority(createdAuthority);
            userRepo.save(registeredUser);

            return converter.convertUserToDTO(registeredUser);
        } else {
            throw new RecordNotFoundException(authorityDTO.getUsername());
        }
    }

    public UserOutputDTO addAuthorityToExistingUser(String username, AuthorityDTO authorityDTO) {
        User existingUser = findUserByUsername(username);
        if (existingUser != null) {
            existingUser.addAuthority(converter.convertToAuthority(authorityDTO));
            userRepo.save(existingUser);

            return converter.convertUserToDTO(existingUser);
        } else {
            throw new RecordNotFoundException(username);
        }
     }

    public boolean doesAuthorityContainAdmin(String username) {
        User foundUser = findUserByUsername(username);

        List<String> listOfAuthorities = new ArrayList<>();

        if (foundUser != null) {

            Set<Authority> loggedInUserAuthorities = foundUser.getAuthorities();

            for (Authority authority : loggedInUserAuthorities) {
                String authorityName = authority.getAuthority();
                listOfAuthorities.add(authorityName);
            }
        }

        return listOfAuthorities.contains("ADMIN");
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        return optionalUser.orElse(null);
    }


}
