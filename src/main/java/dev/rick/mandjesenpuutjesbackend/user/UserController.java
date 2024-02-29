package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend.utils.InputValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final InputValidator validator;

    public UserController(UserService userService, InputValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserInputDTO newUser, BindingResult bindingResult) {

        UserOutputDTO userOutputDTO;

        if (bindingResult.hasFieldErrors()) {
            String responseBody = validator.inputValidator(bindingResult);
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            userOutputDTO = userService.registerNewUser(newUser);
        }

        String username = userOutputDTO.getUsername();
        String authority = "USER";

        boolean isAuthorityAdded = userService.addAuthorityToUser(username, authority);
        String uri;
        if (!isAuthorityAdded) {
            throw new RecordNotFoundException(username);
        }

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/user/"+username)
                .build().toUri())
                .body(userOutputDTO);

    }
}
