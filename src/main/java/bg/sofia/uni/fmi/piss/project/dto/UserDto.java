package bg.sofia.uni.fmi.piss.project.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

public class UserDto {
  private Long userId;
  @NotNull
  private String name;
  @NotNull
  @Email
  private String email;
  @NotNull
  private String password;
  private String role;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
