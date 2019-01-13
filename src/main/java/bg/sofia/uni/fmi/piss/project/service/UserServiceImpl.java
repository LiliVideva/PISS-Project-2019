package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.dto.UserDto;
import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MailService mailService;

  @Autowired
  private UserAssembler userAssembler;

  public ResponseEntity<UserDto> register(UserDto userDto) {

    User existingUser = userRepository.findByUsername(userDto.getEmail());
    if (existingUser != null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    User user = userAssembler.toUser(userDto);
    mailService.sendEmail(user.getEmail(), "Successful registration in Lili and Eva's site!");
    userRepository.save(user);

    return new ResponseEntity<>(userAssembler.toUserDto(user), HttpStatus.CREATED);
  }

  public ResponseEntity login(UserDto userDto) {
    User user = userRepository.findByUsername(userDto.getName());
    if (user == null) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity(HttpStatus.OK);
  }

  public User getAuthUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
    return userRepository.findByUsername(name);
  }

}
