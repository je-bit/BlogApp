package com.example.BlogApp.controllers;

import com.example.BlogApp.models.Blog;
import com.example.BlogApp.services.BlogService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("blogs")
public class BlogController {

    private final BlogService blogService;


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @ModelAttribute
    public Blog blog() {
        return new Blog();
    }

    @GetMapping("/all")
    public ModelAndView allBlogs() {
        ModelAndView modelAndView = new ModelAndView("allBlogs");
        modelAndView.addObject("blogs", this.blogService.findAll());
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView searchBlogs(@Valid String title, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("allBlogs");
        modelAndView.addObject("blogs", this.blogService.searchTitle(title));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addBlog() {
        ModelAndView modelAndView = new ModelAndView("addBlog");

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addBlogPost(@Valid Blog blog, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("addBlog");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("blogs", blog);
            return modelAndView;
        }
        this.blogService.addBlog(blog);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String editGET(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("blog", this.blogService.findById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editPOST(@Valid Blog blog, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("blog", blog);
            return "edit";
        }
        blogService.edit(blog);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteGET(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        this.blogService.deleteBlog(id);
        return "redirect:/";
    }
}
