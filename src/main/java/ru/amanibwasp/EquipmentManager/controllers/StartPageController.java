package ru.amanibwasp.EquipmentManager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.amanibwasp.EquipmentManager.models.*;
import ru.amanibwasp.EquipmentManager.services.EquipmentService;
import ru.amanibwasp.EquipmentManager.services.EventService;
import ru.amanibwasp.EquipmentManager.services.PersonService;
import ru.amanibwasp.EquipmentManager.services.TakenEquipmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class StartPageController {
    private final PersonService personService;
    private final EventService eventService;
    private final EquipmentService equipmentService;
    private final TakenEquipmentService takenEquipmentService;
    @GetMapping
    public String start_page(Model model){

        ArrayList<Person> guest_applications = (ArrayList<Person>) personService.findGuests();
        ArrayList<Equipment> equipment = (ArrayList<Equipment>) equipmentService.index();
        ArrayList<TakenEquipment> takenEquipment = (ArrayList<TakenEquipment>) takenEquipmentService.index();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person current_user = (Person) authentication.getPrincipal();

        List<Event> events_user_is_in = eventService.findParticipating(current_user.getId());



        model.addAttribute("events", events_user_is_in);
        model.addAttribute("equipment_responsible_for", equipment);
        switch (current_user.getRole()) {
            case ROLE_ADMIN -> {
                model.addAttribute("guest_applications", guest_applications);
                return "startPage/startPageForAdmin";
            }
            case ROLE_USER -> {
                return "startPage/startPageForUser";
            }
            default -> {
                return "startPage/startPageForGuest";
            }
        }

    }
}
