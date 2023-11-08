package ru.amanibwasp.EquipmentManager.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amanibwasp.EquipmentManager.models.Equipment;
import ru.amanibwasp.EquipmentManager.models.Person;
import ru.amanibwasp.EquipmentManager.services.PersonService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public String index(Model model) {
        List<Person> peopleList = personService.index();
        model.addAttribute("peopleList", peopleList);
        return "/person/allPeople";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Optional<Person> personToBeUpdated = personService.show(id);
        model.addAttribute("person", personToBeUpdated);
        return "/person/updatePerson";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        Optional<Person> person = personService.show(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "/person/showPerson";
        }
        return "error";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("person") Person person) {
        return "/person/addPerson";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personService.create(person);
        return "redirect:/person";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("person") Person personUpdated){
        personUpdated.setId(id);
        personService.update(personUpdated);
        return "redirect:/person";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        personService.delete(id);
        return "redirect:/person";
    }
}
