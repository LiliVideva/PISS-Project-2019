package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.TaskService;
import bg.sofia.uni.fmi.piss.project.service.TheoreticalKnowledgeService;
import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller("UINoSolutionTaskController")
public class NoSolutionTaskController extends TaskController {
    @Autowired
    private TheoreticalKnowledgeService theoreticalKnowledgeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/noSolutionTasksRegistered.html")
    public String showNoSolutionTasksList(Model model) {
        List<Task> tasks = taskService.list();

        if (tasks.isEmpty()) {
            return homePage();
        }

        if (!model.containsAttribute("tasks")) {
            model.addAttribute("tasks", tasks);
        }
        return "noSolutionTasks.html";
    }

    @GetMapping("/offerSolutionRegistered.html/{taskId}")
    public String showOffer(@PathVariable Long taskId, Model model) {
        Result<Task> result = taskService.getOfferedTaskDetails(taskId);

        if (result.isNotFound()) {
            return homePage();
        }

        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm(result.getEntity()));
            model.addAttribute("theoryOptions", theoreticalKnowledgeService.getTheoreticalKnowledgeOptions());
        }

        return "offerSolutionRegistered.html/details";
    }

    @PostMapping("/offerSolutionRegistered.html/{taskId}")
    @PreAuthorize("isAuthenticated()")
    public String processOffer(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                               RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/offerSolutionRegistered.html/%s", taskId, form, binding, redirect);
        }

        Result<Void> result = taskService.offer(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/offerSolutionRegistered.html/%s", taskId, form, binding, redirect);
        }

        return redirect("/noSolutionTasksRegistered.html");
    }

    @GetMapping("/approveSolutionAdmin.html/{taskId}")
    public String showApprove(@PathVariable Long taskId, Model model) {
        Result<Task> result = taskService.getOfferedTaskDetails(taskId);

        if (result.isNotFound()) {
            return homePage();
        }

        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm(result.getEntity()));
        }
        return "approveSolutionAdmin.html/details";
    }

    @PostMapping("/approveSolutionAdmin.html/{taskId}")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String processApprove(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                                 RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/approveSolutionAdmin.html/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.approve(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/approveSolutionAdmin.html/%s", taskId, form, binding, redirect);
        }

        return redirect("/noSolutionTasksAdmin.html");
    }

    @PostMapping("/noSolutionTasksAdmin.html/{taskId}")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String processUpdate(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                                RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/noSolutionTasksAdmin.html/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.update(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/noSolutionTasksAdmin.html/%s", taskId, form, binding, redirect);
        }

        return redirect("/main.html");
    }

    @DeleteMapping(value = "/noSolutionTasksAdmin.html")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    @ResponseBody
    public ResponseEntity<Void> processDelete(@PathVariable Long taskId) {
        Result<Void> result = taskService.delete(taskId);
        return new ResponseEntity<>(result.getEntity(), result.getHttpStatus());
    }
}
