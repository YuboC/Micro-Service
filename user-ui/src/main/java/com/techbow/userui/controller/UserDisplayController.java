package com.techbow.userui.controller;

import com.techbow.userui.model.User;
import com.techbow.userui.service.I18nService;
import com.techbow.userui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Locale;

@Controller
public class UserDisplayController {
    @Autowired
    private I18nService i18nService;
    @Autowired
    private UserService userService;

    private void populateLanguageForModel(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        String identifierLabel = i18nService.getTranslation("identifier", locale.getLanguage());
        String nameLabel = i18nService.getTranslation("name", locale.getLanguage());
        String emailLabel = i18nService.getTranslation("email", locale.getLanguage());
        model.addAttribute("identifierLabel", identifierLabel);
        model.addAttribute("nameLabel", nameLabel);
        model.addAttribute("emailLabel", emailLabel);
    }

    @GetMapping("/user_list")
    public String displayUsers(Model model) {
        this.populateLanguageForModel(model);

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "UserList.html";
    }

    @GetMapping("/user_input")
    public String inputUser(Model model) {
        this.populateLanguageForModel(model);

        return "UserInput.html";
    }

    @GetMapping("/user_display/{id}")
    public String displayUser(Model model, @PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            return errorDisplayUser(model, id);
        }

        this.populateLanguageForModel(model);
        model.addAttribute("user", user);

        return "UserDisplay.html";
    }

    @PostMapping("/user_input")
    public RedirectView saveUser(User user, @NonNull BindingResult result) {
        if (result.hasErrors()) {
            return new RedirectView("/user_error", true);
        }

        User saved = userService.saveUser(user);
        return new RedirectView("/user_display/" + saved.getId().toString(), true);
    }

    @GetMapping("/user_error")
    public String errorDisplayUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", Long.toString(id));
        return "UserError.html";
    }
}
