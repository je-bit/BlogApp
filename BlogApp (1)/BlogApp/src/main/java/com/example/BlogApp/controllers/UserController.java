package com.example.BlogApp.controllers;

import com.example.BlogApp.models.UserBlog;
import com.example.BlogApp.services.BlogService;
import com.example.BlogApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BlogService blogService;

    public UserController(UserService userService, BlogService blogService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @ModelAttribute
    public UserBlog userBlog() {
        return new UserBlog();
    }
    @GetMapping("/login")
    public ModelAndView loginGET(RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginPOST(@Valid UserBlog userBlog, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView("login");

                modelAndView.addObject("userBlog", userBlog);

                return modelAndView;
            }

            userService.loginUser(userService.findByUsername(userBlog.getUsername()));
            ModelAndView modelAndView = new ModelAndView("index");
            modelAndView.addObject("blogs", blogService.findAll());

            return modelAndView;
        } catch (RuntimeException error) {
            ModelAndView modelAndView = new ModelAndView("login");

//            modelAndView.addObject("userBlog", userBlog);
            bindingResult.rejectValue("username", "error.userBlog", "Потребителското име или паролата не е вярно.");
            bindingResult.rejectValue("password", "error.userBlog", "Потребителското име или паролата не е вярно.");

            return modelAndView;
        }
    }

}
