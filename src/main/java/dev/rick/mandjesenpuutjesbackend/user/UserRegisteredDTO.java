package dev.rick.mandjesenpuutjesbackend.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredDTO {

    private String username;
    private boolean enabled;
    private String firstname;
}
