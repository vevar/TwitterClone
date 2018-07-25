package com.example.twitterclone.controllers;


import com.example.twitterclone.domain.Role;
import com.example.twitterclone.domain.User;
import com.example.twitterclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.userList());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", userService.allRoles());

        return "userEditor";
    }

    @PostMapping
    public String saveUser(@RequestParam("userId") User user,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username){
        user.setUsername(username);

        Set<String> roles = userService.allRoles();

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.getUserRepository().save(user);

        return "redirect:/user";
    }
}
