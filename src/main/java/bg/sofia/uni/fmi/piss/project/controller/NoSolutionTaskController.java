package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.TaskService;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller("UINoSolutionTaskController")
@RequestMapping("/noSolutionTasks")
public class NoSolutionTaskController extends TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public String processUpdate(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                                RedirectAttributes redirect) {
        if (taskService.validateForm(form, binding)) {
            return showFormErrors("/noSolutionTasks/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.update(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/noSolutionTasks/%s", taskId, form, binding, redirect);
        }

        return redirect("/main");
    }

    @PostMapping("/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public String processSolve(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                               RedirectAttributes redirect) {
        if (taskService.validateForm(form, binding)) {
            return showFormErrors("/noSolutionTasks/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.solve(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/noSolutionTasks/%s", taskId, form, binding, redirect);
        }

        return redirect("/main");
    }
}
