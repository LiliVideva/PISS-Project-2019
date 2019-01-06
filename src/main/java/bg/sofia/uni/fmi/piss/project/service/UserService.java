package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.form.LoginForm;
import bg.sofia.uni.fmi.piss.project.form.RegisterForm;
import bg.sofia.uni.fmi.piss.project.service.result.Result;

import java.util.List;

public interface UserService {
    List<User> list();

    Result<User> register(RegisterForm registerForm);

    Result<User> login(LoginForm loginForm);
}
