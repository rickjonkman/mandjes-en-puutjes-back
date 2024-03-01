package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.NameIsTakenException;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UserService userService;

    UserInputDTO userInputDTO = new UserInputDTO();
    User registeredUser = new User();
    String[] authorities = new String[1];
    Set<Authority> authoritySet = new HashSet<>();

    @BeforeEach
    public void setUp() {
        userInputDTO.setUsername("bart@novi.nl");
        userInputDTO.setPassword("password");
        userInputDTO.setFirstname("Bart");
        userInputDTO.setEnabled(true);

        registeredUser.setUsername(userInputDTO.getUsername());
        registeredUser.setPassword(userInputDTO.getPassword());
        registeredUser.setFirstname(userInputDTO.getFirstname());
        registeredUser.setEnabled(userInputDTO.isEnabled());

        authoritySet.add(new Authority("bart@novi.nl", "USER"));
        authorities[0] = "USER";
    }


    @Test
    void shouldSaveCorrectUser() {

        UserOutputDTO userOutputDTO = new UserOutputDTO(
                registeredUser.getUsername(),
                registeredUser.getFirstname(),
                registeredUser.isEnabled(),
                authorities
        );

        when(userConverter.convertToUser(userInputDTO)).thenReturn(registeredUser);
        when(userRepository.save(any())).thenReturn(registeredUser);

        UserOutputDTO actualOutput = userService.registerNewUser(userInputDTO);

        verify(userRepository, times(1)).save(any());

        assertEquals(actualOutput.getUsername(), userInputDTO.getUsername());
        assertEquals(actualOutput.getFirstname(), userInputDTO.getFirstname());
        assertEquals(actualOutput.getAuthorities(), authorities);
    }

    @Test
    void shouldThrowExceptionIfUsernameIsTaken() {
        String newUsername = "bart@novi.nl";
        String newPassword = "wordpass";
        String newFirstname = "Bart";
        boolean newEnabled = true;

        User existingUser = new User();
        existingUser.setUsername(newUsername);

        UserInputDTO newUserDTO = new UserInputDTO(newUsername, newPassword, newFirstname, newEnabled);

        when(userRepository.findById(newUsername)).thenReturn(Optional.of(existingUser)); // Correct stub

        assertThrows(NameIsTakenException.class, () -> userService.registerNewUser(newUserDTO));
    }



}