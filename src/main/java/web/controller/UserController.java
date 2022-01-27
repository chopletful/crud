package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {

    private UserService users;

    @Autowired
    public void setUserService(UserService userService) {
        this.users = userService;
    }

    @GetMapping(value = "/users")
    public String showUsers(ModelMap model){
        List<User> allUsers = users.allUsers();
        model.addAttribute("allUsers", allUsers);
        return "user";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = users.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @PostMapping(value = "/edit")
    public String create(@ModelAttribute("user") User user) {
        users.add(user);
        return "redirect:/users";
    }


    @GetMapping(value = "/new")
    public String newUser(ModelMap model){
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("user") User user){
        users.add(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") int id) {
        User user = users.getById(id);
        users.delete(user);
        return "redirect:/users";
    }
}
