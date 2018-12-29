package bg.sofia.uni.fmi.piss.project.controller;

import bg.sofia.uni.fmi.piss.project.service.result.Result;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class UiController extends Routes {

    static void reject(BindingResult bindingResult, Result<?> result) {
        bindingResult.rejectValue(result.getField(), result.getMessage(), result.getMessage());
    }

    String showFormErrors(String path, Long resourceId, Object form, BindingResult binding, RedirectAttributes redirect) {
        redirect.addFlashAttribute("org.springframework.validation.BidingResult.form", binding);
        redirect.addFlashAttribute("form", form);
        return (resourceId == null) ? redirect(path) : redirect(path, resourceId);
    }

    String showFormErrors(String path, Object form, BindingResult binding, RedirectAttributes redirect) {
        return showFormErrors(path, null, form, binding, redirect);
    }

    String showErrorPage(Integer errorCode, String errorMessage, Model model) {
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
