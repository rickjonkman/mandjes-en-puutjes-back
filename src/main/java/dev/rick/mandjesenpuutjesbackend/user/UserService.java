package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserConverter converter;

    public UserOutputDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new NameIsTakenException(newUser.getUsername());
        } else {
            User createdUser = converter.convertToUser(newUser);
            userRepo.save(createdUser);
            return converter.convertUserToDTO(createdUser);
        }
    }

    public boolean addAuthorityToUser(String username, String authorityName) {
        User doesUserExist = findUserByUsername(username);
        if (doesUserExist != null) {
            doesUserExist.addAuthority(new Authority(username, authorityName));
            return true;
        } else {
            return false;
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        return optionalUser.orElse(null);
    }
}
