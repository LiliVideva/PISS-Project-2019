package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.dto.TaskDto;
import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.TaskService;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller("UITaskController")
@RequestMapping("/tasks")
public class TaskController extends UiController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{taskId}")
    public String showDetails(@PathVariable Long taskId, Model model) {
        Result<TaskDto> result = taskService.getDetails(taskId);

        if (result.isNotFound()) {
            return homePage();
        }

        model.addAttribute("task", result.getEntity().getTask().getTitle());
        model.addAttribute("content", result.getEntity().getTask().getContent());
        model.addAttribute("theoreticalKnowledges", taskService.getTheoreticalKnowledgeOptions());
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm(result.getEntity()));
        }
        return "task/details";
    }

    @GetMapping("/tasks/add-task")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String showAddTask(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm());
        }
        return "task/add";
    }

    @PostMapping("/tasks/add-task")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String processAddTask(@PathVariable Long taskId, @Validated(value = TaskForm.Add.class) @ModelAttribute("form") TaskForm form, BindingResult binding,
                                 RedirectAttributes redirect) {
        if (taskService.validateForm(form, binding)) {
            return showFormErrors("/tasks/%s/add-task", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.add(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/tasks/%s/add-task", taskId, form, binding, redirect);
        }

        return redirect("/tasks");
    }

    @PostMapping("/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public String processUpdate(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                                RedirectAttributes redirect) {
        if (taskService.validateForm(form, binding)) {
            return showFormErrors("/tasks/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.update(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/tasks/%s", taskId, form, binding, redirect);
        }

        return redirect("/main");
    }

    @PostMapping("/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public String processSolve(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                               RedirectAttributes redirect) {
        if (taskService.validateForm(form, binding)) {
            return showFormErrors("/tasks/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.solve(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/tasks/%s", taskId, form, binding, redirect);
        }

        return redirect("/main");
    }

    @DeleteMapping(value = "/{taskId}")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    @ResponseBody
    public ResponseEntity<Void> processDelete(@PathVariable Long taskId) {
        Result<Void> result = taskService.delete(taskId);
        return new ResponseEntity<>(result.getEntity(), result.getHttpStatus());
    }
}
