package dev.rick.mandjesenpuutjesbackend.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserOutputDTO {

    private String username;
    private String firstname;
    private boolean enabled;
    private String[] authorities;
}
