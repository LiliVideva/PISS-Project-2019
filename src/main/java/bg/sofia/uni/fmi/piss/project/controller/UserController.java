package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.entity.User;
import bg.sofia.uni.fmi.piss.project.form.UserForm;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller("UIUserController")
@RequestMapping("/users")
public class UserController extends UiController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String showList(Model model) {
        model.addAttribute("users", userService.list());
        return "user/list";
    }

    @GetMapping("/add")
    public String showAddUser(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new UserForm());
        }
        return "user/add";
    }

    @PostMapping("/add")
    public String processAddUser(@Valid @ModelAttribute("form") UserForm form, BindingResult binding, RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/users/add", form, binding, redirect);
        }

        Result<User> result = userService.add(form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/users/add", form, binding, redirect);
        }
        return redirect("/users");
    }
}
