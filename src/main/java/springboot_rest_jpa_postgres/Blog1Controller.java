package springboot_rest_jpa_postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller //Controller + thymeleaf
public class Blog1Controller {
    @Autowired
    BlogRespository blogRespository;

    //@GetMapping(path="/blog1")         //or, @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @RequestMapping("/")
    public String index(Model model){
        List<Blog> listBlog = blogRespository.findAll();
        model.addAttribute("listBlog", listBlog);
        return "index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView show(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("blog",blogRespository.findById(id).get());
        return mav;
    }

    @GetMapping("/blog1/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogRespository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @GetMapping("/create")
    public String showNewCreatePage(Model model) {
        model.addAttribute("blog", new Blog());
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("blog") Blog blog) {
        blogRespository.save(blog);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") int id){
        blogRespository.deleteById(id);
        return "redirect:/";
    }
}
