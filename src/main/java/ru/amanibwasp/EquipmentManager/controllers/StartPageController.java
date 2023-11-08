package ru.amanibwasp.EquipmentManager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.amanibwasp.EquipmentManager.models.Event;
import ru.amanibwasp.EquipmentManager.models.Person;
import ru.amanibwasp.EquipmentManager.services.EventService;
import ru.amanibwasp.EquipmentManager.services.PersonService;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class StartPageController {
    private final PersonService personService;
    private final EventService eventService;

    @GetMapping
    public String start_page(Model model){
        ArrayList<Person> guest_applications = (ArrayList<Person>) personService.findGuests();
        ArrayList<Event> events = (ArrayList<Event>) eventService.index();
        model.addAttribute("guest_applications", guest_applications);
        model.addAttribute("events", events);
        return "startPage/startPage";
    }
}
