package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Blog;
import rikkei.academy.model.Catalog;
import rikkei.academy.service.blog.IBlogService;
import rikkei.academy.service.catalog.ICatalogService;

import java.util.Optional;


@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICatalogService catalogService;

    @GetMapping("/")
    public ModelAndView Home(Pageable pageable){
        Page<Blog> list = blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("blog",list);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("catalogs",catalogService.findAll());
        return modelAndView;
    }

    @PostMapping("/creates")
    public String saveBlog(@ModelAttribute("blog") Blog blog, @ModelAttribute("catalogs") Catalog catalogs) {
        blogService.save(blog);
        return "redirect:/create";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable("id") Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("blogs", blog.get());
            return modelAndView;
        }else {
            return new ModelAndView("/home");
        }
    }

    @PostMapping("/edit")
    public String updateBlog(@ModelAttribute("blogs") Blog blog) {
        blogService.save(blog);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id){
        blogService.remove(id);
        return "redirect:/";
    }
}
