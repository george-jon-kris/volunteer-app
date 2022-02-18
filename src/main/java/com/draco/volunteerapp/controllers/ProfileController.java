package com.draco.volunteerapp.controllers;

import com.draco.volunteerapp.models.User;
import com.draco.volunteerapp.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class ProfileController {
    private UserRepository userRepository;
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @GetMapping("/profile")
    public String profilePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDb = userRepository.getById(user.getId());
        model.addAttribute("user", userInDb);
        return "profile";
    }

    @GetMapping("/profile/view/{id}")
    public String profilePage(Model model, @PathVariable long id) {
        User userInDb = userRepository.getById(id);
        model.addAttribute("user", userInDb);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String showEditPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInDB = userRepository.findByUsername(user.getUsername());
        model.addAttribute("user", userInDB);
        return "edit-profile";

    }

    // update profile
    @PostMapping("/profile/edit")
    public String editProfile(@ModelAttribute User user){
        userRepository.save(user);
        return "profile";
    }


}
