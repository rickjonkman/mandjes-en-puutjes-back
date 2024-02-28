package dev.rick.mandjesenpuutjesbackend.user;

import dev.rick.mandjesenpuutjesbackend.exceptions.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepo.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        } else {
            User foundUser = optionalUser.get();
            String password = foundUser.getPassword();
            Set<Authority> authorities = foundUser.getAuthorities();

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Authority authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
            }

            return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
        }
    }
}
