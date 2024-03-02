package dev.rick.mandjesenpuutjesbackend.auth;

import dev.rick.mandjesenpuutjesbackend.jwt.JwtUtility;
import dev.rick.mandjesenpuutjesbackend.user.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtility jwtUtility;

    @PostMapping("authenticate")
    public ResponseEntity<?> createAccessToken(@RequestBody AuthenticationRequestDTO authRequestDTO) {

        String username = authRequestDTO.getUsername();
        String password = authRequestDTO.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Username or password incorrect.");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        final String jwt = jwtUtility.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }
}
