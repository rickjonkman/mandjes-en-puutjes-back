package dev.rick.mandjesenpuutjesbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequestDTO {

    private String username;
    private String password;


}
