package dev.rick.mandjesenpuutjesbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public record AuthenticationResponseDTO(String jwt) {

}
