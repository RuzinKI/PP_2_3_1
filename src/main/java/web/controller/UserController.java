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

    @GetMapping(value = {"users"})
    public String getUserList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping(value = "/users")
    public String saveStudent(@ModelAttribute("user") User user, Model model) {
        System.out.println("save user");
        userService.add(user);
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping(value = "/user/edit/{id}")
    public String editUserPost(@PathVariable Long id,
                             @ModelAttribute("user") User user,
                             Model model) {
        User oldUser = userService.getById(id);
        oldUser.setId(id);
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());

        userService.update(oldUser);
        model.addAttribute("users", userService.getAll());
        return "redirect:/users";
    }

    @GetMapping(value = {"user/edit/{id}"})
    public String editUser(@PathVariable Long id, Model model) {
        User userEdit = userService.getById(id);
        model.addAttribute("user",userEdit);
        return "edit_user";
    }

    @PostMapping(value = "/user/delete")
    public String deleteUser(@ModelAttribute("user") User user, Model model) {
        userService.delete(user);
        model.addAttribute("users", userService.getAll());
        return "redirect:/users";
    }

    @GetMapping(value = {"users/new"})
    public String createNewUser(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "new_user";
    }

    @GetMapping(value = {"user"})
    public String getOneUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("user", userService.getById(id));
            return "user";
        } else {
            model.addAttribute("users", userService.getAll());
            return "users";
        }
    }

}
