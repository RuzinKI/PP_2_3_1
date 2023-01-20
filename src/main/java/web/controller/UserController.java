package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.entity.User;
import web.service.UserService;

@Controller
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String saveStudent(@ModelAttribute("user") User user) {
        System.out.println("save user");
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String editUser(@PathVariable Long id,
                           Model model) {
        User userEdit = userService.getById(id);
        model.addAttribute("userV",userEdit);
        return "edit_user";
    }

    @PostMapping("/users/edit/{id}")
    public String editUserPost(@PathVariable Long id,
                               @ModelAttribute("user") User user) {
        user.setId(id);
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("users/new")
    public String createNewUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "new_user";
    }
}
