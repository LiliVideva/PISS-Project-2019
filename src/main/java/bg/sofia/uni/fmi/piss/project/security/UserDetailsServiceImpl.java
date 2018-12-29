package bg.sofia.uni.fmi.piss.project.security;

import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.entity.UserRole;
import bg.sofia.uni.fmi.piss.project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndFetchAllEagerly(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : user.getUserRoles()) {
            authorities.add(role.getRole());
        }

        AuthUser authUser = new AuthUser(user.getEmail(), user.getPassword(), authorities);
        authUser.setAccount(user.getAccount());
        authUser.setName(user.getUsername());
        return authUser;
    }
}
