package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.entity.UserRole;
import bg.sofia.uni.fmi.piss.project.enums.Role;
import bg.sofia.uni.fmi.piss.project.form.LoginForm;
import bg.sofia.uni.fmi.piss.project.form.RegisterForm;
import bg.sofia.uni.fmi.piss.project.repository.UserRepository;
import bg.sofia.uni.fmi.piss.project.repository.UserRoleRepository;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> list() {
        return userRepository.findByRoleNotIn(Role.ADMIN.getId());
    }

    @Override
    public Result<User> register(RegisterForm registerForm) {
        User user = new User();

        user.setEmail(registerForm.getEmail());
        user.setUsername(registerForm.getUsername());
        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        user.setUserRoles(getUserRole(Role.USER));

        Result<User> result = add(user);
        if (!result.isOk()) {
            return badRequest();
        }
        return result;
    }

    private Result<User> add(User user) {
        if (isBlank(user.getEmail()) || isBlank(user.getUsername()) || isBlank(user.getPassword())) {
            return badRequest();
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return badRequest("email", "error.email.exists");
        }

        return created(userRepository.save(user));
    }

    private Set<UserRole> getUserRole(Role role) {
        UserRole userRole = userRoleRepository.findByRole(role);
        Set<UserRole> roles = new HashSet<>();

        roles.add(userRole);

        return roles;
    }

    @Override
    public Result<User> login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail());

        if (user == null || !user.getPassword().equals(passwordEncoder.encode(loginForm.getPassword()))) {
            return badRequest();
        }
        return ok(user);
    }

}
