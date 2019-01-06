package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.domain.Mail;
import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.form.LoginForm;
import bg.sofia.uni.fmi.piss.project.form.RegisterForm;
import bg.sofia.uni.fmi.piss.project.service.MailService;
import bg.sofia.uni.fmi.piss.project.service.UserService;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller("UIUserController")
public class UserController extends UiController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String showList(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @GetMapping("/registrationForm.html")
    public String showRegisterUser(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new RegisterForm());
        }
        return "registrationForm.html";
    }

    @PostMapping("/registrationForm.html")
    public String processRegisterUser(@Valid @ModelAttribute("form") RegisterForm form, BindingResult binding, RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/registrationForm.html", form, binding, redirect);
        }

        Result<User> result = userService.register(form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/registrationForm.html", form, binding, redirect);
        }

        mailService.sendMail(new Mail(form.getEmail(), "You are successfully registered in our website! Enjoy the mathematics!",
                "Successful registration"));
        return redirect("/loginForm.html");
    }

    @GetMapping("/loginForm.html")
    public String showLoginUser(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new LoginForm());
        }
        return "loginForm.html";
    }

    @PostMapping("/loginForm.html")
    public String processLoginUser(@Valid @ModelAttribute("form") LoginForm form, BindingResult binding, RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/loginForm.html", form, binding, redirect);
        }

        Result<User> result = userService.login(form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/loginForm.html", form, binding, redirect);
        }
        return redirect("/mainRegistered.html");
    }
}
