package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.dto.UserDto;
import bg.sofia.uni.fmi.piss.project.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user", produces = "application/json", consumes = "application/json")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/registrationForm")
  public ResponseEntity<UserDto> processRegisterUser(@Valid @RequestBody UserDto userDto, BindingResult binding) {
    if (binding.hasErrors()) {
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    return userService.register(userDto);
  }

  @PostMapping("/loginForm")
  public ResponseEntity processLoginUser(@Valid @RequestBody UserDto userDto, BindingResult binding) {
    if (binding.hasErrors()) {
      return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    return userService.login(userDto);
  }
}
