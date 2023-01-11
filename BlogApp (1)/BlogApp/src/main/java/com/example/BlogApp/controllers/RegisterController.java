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
public class RegisterController {

    private final UserService userService;
    private final BlogService blogService;

    public RegisterController(UserService userService, BlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

    @ModelAttribute
    public UserBlog userBlog() {
        return new UserBlog();
    }

    @GetMapping("/sign")
    public ModelAndView registerGET(RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("signUp");

        return modelAndView;
    }

    @PostMapping("/sign")
    public ModelAndView registerPOST(@Valid UserBlog userBlog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("signUp");

            modelAndView.addObject("userBlog", userBlog);

            return modelAndView;
        }

        userService.register(userBlog);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("blogs", blogService.findAll());
        return modelAndView;
    }
}
