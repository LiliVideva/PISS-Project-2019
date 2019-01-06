package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.entity.Task;
import bg.sofia.uni.fmi.piss.project.form.TaskForm;
import bg.sofia.uni.fmi.piss.project.service.DifficultyService;
import bg.sofia.uni.fmi.piss.project.service.PartService;
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

@Controller("UITaskController")
public class TaskController extends UiController {
    @Autowired
    private DifficultyService difficultyService;

    @Autowired
    private PartService partService;

    @Autowired
    private TheoreticalKnowledgeService theoreticalKnowledgeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/mainRegistered.html")
    public String showList(@PathVariable Long difficultyId, Model model) {
        List<Task> tasks = taskService.list(difficultyId);

        if (tasks.isEmpty()) {
            return homePage();
        }

        if (!model.containsAttribute("tasks")) {
            model.addAttribute("tasks", tasks);
        }
        return "mainRegistered.html";
    }

    @GetMapping("/trainingTaskRegistered.html/{taskId}")
    public String showDetails(@PathVariable Long taskId, Model model) {
        Result<Task> result = taskService.getDetails(taskId);

        if (result.isNotFound()) {
            return homePage();
        }

        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm(result.getEntity()));
        }
        return "trainingTaskRegistered.html/details";
    }

    @PostMapping("/trainingTaskRegistered.html/solve/{taskId}")
    public String processSolve(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                               RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/trainingTaskRegistered.html/solve/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.solve(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("trainingTaskRegistered.html/solve/%s", taskId, form, binding, redirect);
        }

        return redirect("/mainRegistered.html");
    }

    @GetMapping("/addTaskAdmin.html")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String showAdd(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new TaskForm());
            model.addAttribute("difficultyOptions", difficultyService.getDifficultyOptions());
            model.addAttribute("partOptions", partService.getPartOptions());
            model.addAttribute("theoryOptions", theoreticalKnowledgeService.getTheoreticalKnowledgeOptions());
        }
        return "addTaskAdmin.html";
    }

    @PostMapping("/addTaskAdmin.html")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String processAdd(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                             RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/addTaskAdmin.html/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.add(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/addTaskAdmin.html/%s", taskId, form, binding, redirect);
        }

        return redirect("/mainAdmin.html");
    }

    @PostMapping("/trainingTaskAdmin.html/update/{taskId}")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    public String processUpdate(@PathVariable Long taskId, @Valid @ModelAttribute("form") TaskForm form, BindingResult binding,
                                RedirectAttributes redirect) {
        if (binding.hasErrors()) {
            return showFormErrors("/trainingTaskAdmin.html/update/%s", taskId, form, binding, redirect);
        }

        Result<Task> result = taskService.update(taskId, form);

        if (!result.isOk()) {
            reject(binding, result);
            return showFormErrors("/trainingTaskAdmin.html/update/%s", taskId, form, binding, redirect);
        }

        return redirect("/mainAdmin.html");
    }

    @DeleteMapping(value = "/trainingTaskAdmin.html")
    @PreAuthorize("hasAnyRole({'ADMIN'})")
    @ResponseBody
    public ResponseEntity<Void> processDelete(@PathVariable Long taskId) {
        Result<Void> result = taskService.delete(taskId);
        return new ResponseEntity<>(result.getEntity(), result.getHttpStatus());
    }
}
