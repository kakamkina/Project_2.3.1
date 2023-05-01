package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDaoImp;
import web.model.User;

@Controller
@RequestMapping("users")
public class UserController {


    private final UserDaoImp userDao;

    @Autowired
    public UserController(UserDaoImp userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDao.getAllUsers());
        return "users/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userDao.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDao.index(id));
        return "users/index";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        User user = userDao.findById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("{id}/edit")
    public String edit(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userDao.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id ) {
        userDao.deleteUser(id);
        return "redirect:/users";
    }
}
