package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.UserDto;
import bg.sofia.uni.fmi.piss.project.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<UserDto> register(UserDto userDto);

  ResponseEntity login(UserDto userDto);

  User getAuthUser();
}
