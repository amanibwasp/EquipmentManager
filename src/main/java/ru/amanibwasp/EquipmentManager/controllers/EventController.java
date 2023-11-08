package ru.amanibwasp.EquipmentManager.controllers;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amanibwasp.EquipmentManager.forms.ResponsibilitiesForm;
import ru.amanibwasp.EquipmentManager.models.*;
import ru.amanibwasp.EquipmentManager.services.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final PersonService personService;
    private final EventService eventService;
    private final EquipmentService equipmentService;
    private final PersonAndEventService personAndEventService;
    private final TakenEquipmentService takenEquipmentService;


    @GetMapping
    public String index(Model model) {
        List<Event> eventsList = eventService.index();
        model.addAttribute("eventsList", eventsList);
        return "/event/allEvents";
    }

    @PostMapping("/new/save")
    public String saveEquipment(@ModelAttribute("event") Event event){
        eventService.create(event);
        for (Person person: event.getParticipants()) {
            PersonAndEvent personAndEvent = new PersonAndEvent();
            personAndEvent.setEvent_id(event.getId());
            personAndEvent.setPerson_id(person.getId());
            personAndEventService.create(personAndEvent);
            for (Equipment equipment: event.getChosen_equipment()) {
                TakenEquipment takenEquipment = new TakenEquipment();
                takenEquipment.setEvent_id(event.getId());
                takenEquipment.setEquipment_id(equipment.getId());
                takenEquipment.setPerson_id(null);
                takenEquipment.setCnt(equipment.getCnt());
                takenEquipmentService.create(takenEquipment);
            }
        }
        return "redirect:/event";
    }
    @GetMapping("/new")
    public String createForm(Model model) {
        ArrayList<Person> people = (ArrayList<Person>) personService.index();
        ArrayList<Equipment> equipment = (ArrayList<Equipment>) equipmentService.index();
        model.addAttribute("people", people);
        model.addAttribute("all_equipment", equipment);
        model.addAttribute("event", new Event());
        return "/event/addEvent";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        Optional<Event> event = eventService.show(id);
        if (event.isPresent()){
            Event choosen_event = event.get();
            HashSet<Person> participants = (HashSet<Person>) personAndEventService.findParticipants(choosen_event.getId());
            choosen_event.setParticipants(participants);
            model.addAttribute("event", choosen_event);
            return "/event/showEvent";
        }
        return "error";
    }

    @GetMapping("/{id}/responsibilities")
    public String responsibilities(@PathVariable Long id, Model model){

        List<Equipment> chosen_equipment = takenEquipmentService.findChosenEquipment(id);
        List<Person> people = personService.index();
        ResponsibilitiesForm responsibilitiesForm = new ResponsibilitiesForm();

        responsibilitiesForm.setPeople(people);
        responsibilitiesForm.setEquipmentList(chosen_equipment);

        model.addAttribute("people", people);
        model.addAttribute("form", responsibilitiesForm);
        return "/event/responsibilities";
    }

    @PostMapping("/{id}/responsibilities")
    public String responsibilities_save(@PathVariable Long id,
                                        @ModelAttribute("form") ResponsibilitiesForm form){
        //Saving id of chosen person mapped to the equipment
        return "redirect:/event";
    }

    @PostMapping
    public String create(@ModelAttribute("event") Event event){
        eventService.create(event);
        for (Person person:
             event.getParticipants()) {
            PersonAndEvent personAndEvent = PersonAndEvent.builder()
                    .event_id(event.getId())
                    .person_id(person.getId())
                    .build();
            personAndEventService.create(personAndEvent);
        }
        return "redirect:/event";
    }


    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        eventService.delete(id);
        return "redirect:/event";
    }
}
