package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.UserDto;
import bg.sofia.uni.fmi.piss.project.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

  public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
  private static final String defaultRole = "ROLE_USER";

  User toUser(UserDto userDto) {
    return User.builder()
        .username(userDto.getName())
        .email(userDto.getEmail())
        .password(PASSWORD_ENCODER.encode(userDto.getPassword()))
        .role(defaultRole)
        .build();
  }

  UserDto toUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setUserId(user.getId());
    userDto.setName(user.getUsername());
    userDto.setEmail(user.getEmail());
    return userDto;
  }
}
