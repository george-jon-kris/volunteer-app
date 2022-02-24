package com.draco.volunteerapp.controllers;

import com.draco.volunteerapp.models.User;
import com.draco.volunteerapp.repositories.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

    private UserRepository usersDao;

    public HomeController(UserRepository usersDao) {

        this.usersDao = usersDao;
    }



    @GetMapping({"/"})
        public String getSplashPage(){
            return "partials/splash";
    }
    @GetMapping({"/home"})
    public String getIndexPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userInDb = usersDao.getById(user.getId());
            model.addAttribute("userId", user.getId());

        }

    }



}