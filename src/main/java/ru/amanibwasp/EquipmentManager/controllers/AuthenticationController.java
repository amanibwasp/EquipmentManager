package ru.amanibwasp.EquipmentManager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.amanibwasp.EquipmentManager.models.Person;
import ru.amanibwasp.EquipmentManager.repositories.PersonRepository;
import ru.amanibwasp.EquipmentManager.services.PersonService;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final PersonService personService;

    @GetMapping("/login")
    public String login(){
        return "/auth/login";
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("person") Person person){
        return "/auth/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("person") Person person){
        personService.create(person);
        return "redirect:/";
    }

}
