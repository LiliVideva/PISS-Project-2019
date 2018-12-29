package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.entity.UserRole;
import bg.sofia.uni.fmi.piss.project.enums.Role;
import bg.sofia.uni.fmi.piss.project.form.UserForm;
import bg.sofia.uni.fmi.piss.project.repository.UserRepository;
import bg.sofia.uni.fmi.piss.project.repository.UserRoleRepository;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return userRepository.findByAccountAndRoleNotIn(getAuthenticatedUser().getAccount(), Role.ADMIN.getId());
    }

    @Override
    public Result<User> add(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
        user.setUserRoles(getUserRole(Role.USER));
        user.setAccount(getAuthenticatedUser().getAccount());

        Result<User> result = add(user);
        if (result.isOk()) {

        }
        return result;
    }

    private Result<User> add(User user) {
        if (isBlank(user.getEmail()) || isBlank(user.getUsername()) || user.getAccount() == null || user.getUserRoles().isEmpty()) {
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
}
