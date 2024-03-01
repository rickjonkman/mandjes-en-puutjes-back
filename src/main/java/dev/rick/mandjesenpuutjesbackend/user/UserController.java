package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend.utils.InputValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;


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
        if (!isAuthorityAdded) {
            throw new RecordNotFoundException(username);
        }

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/v1/user/"+username)
                .build().toUri())
                .body(userOutputDTO);

    }

    @GetMapping("/user/get")
    public ResponseEntity<UserOutputDTO> getUserById(@RequestParam(name = "username") String username) {
        UserOutputDTO outputDTO = userService.getUserById(username);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/user/get-preferences")
    public ResponseEntity<UserPreferencesDTO> getUserPreferencesById(@RequestParam(name = "username") String username) {
        UserPreferencesDTO outputDTO = userService.getUserPreferencesById(username);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/user/change-preferences")
    public ResponseEntity<UserPreferencesDTO> changeUserPreferences(@RequestParam(name = "username") String username, @RequestParam UserPreferencesDTO inputDTO) {
        UserPreferencesDTO outputDTO = userService.changeUserPreferences(username, inputDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/admin/add-admin-authority")
    public ResponseEntity<String> addAdminAuthorityAsAdmin(Principal principal, @RequestParam(name = "username") String username) {
        String confirmOutput = "Authority added to user: "+username;

        boolean confirmation = userService.addAdminAuthorityAsAdmin(principal, username);
        if (confirmation) {
            return ResponseEntity.ok(confirmOutput);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/admin/delete-user")
    public ResponseEntity<String> deleteUserAsAdmin(Principal principal, @RequestParam(name = "username") String username) {
        String confirmOutput = "User succesfully deleted: "+username;

        boolean confirmation = userService.deleteUserAsAdmin(principal, username);
        if (confirmation) {
            return ResponseEntity.ok(confirmOutput);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
