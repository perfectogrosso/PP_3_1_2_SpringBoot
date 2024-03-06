package mvc.springbootapp2.controllers;

import mvc.springbootapp2.dao.UserDAO;
import mvc.springbootapp2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class MainController {
    private final UserDAO userDAO;

    @Autowired
    public MainController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value="userId", required = false) Integer userId){
        if(userId != null){
            model.addAttribute("user",userDAO.show(userId));
            return "user";
        }
        model.addAttribute("users", userDAO.index());
        return "index";
    }
    @GetMapping("/new")
    public String create(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }

    @PostMapping()
    public String newUser(@ModelAttribute User user){
        userDAO.create(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String editUser(Model model, @RequestParam(value="userId", required = false) Integer userId){
        model.addAttribute("user", userDAO.show(userId));
        return "edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute User user, @RequestParam(value="userId", required = false) Integer userId){
        userDAO.update(userId, user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam(value="userId", required = false) Integer userId){
        userDAO.delete(userId);
        return "redirect:/users";
    }
}
