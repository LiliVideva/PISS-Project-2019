package bg.sofia.uni.fmi.piss.project.form;

import bg.sofia.uni.fmi.piss.project.util.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegisterForm {
    private String email;

    @NotNull
    @Length(min = Constants.MIN_NAME_LENGTH, max = Constants.MAX_NAME_LENGTH)
    private String username;

    @NotNull
    @Length(min = Constants.MIN_NAME_LENGTH, max = Constants.MAX_NAME_LENGTH)
    private String password;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
