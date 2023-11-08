package ru.amanibwasp.EquipmentManager.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amanibwasp.EquipmentManager.services.TakenEquipmentService;
import ru.amanibwasp.EquipmentManager.models.TakenEquipment;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/takenequipment")
public class TakenEquipmentController {
    private TakenEquipmentService takenEquipmentService;

    @Autowired
    public TakenEquipmentController(TakenEquipmentService takenEquipmentService) {
        this.takenEquipmentService = takenEquipmentService;
    }

    @GetMapping()
    public String index(Model model) {
        List<TakenEquipment> takenEquipmentList = takenEquipmentService.index();
        model.addAttribute("takenEquipmentList", takenEquipmentList);
        return "/takenEquipment/allTakenEquipment";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Optional<TakenEquipment> tkToBeUpdated = takenEquipmentService.show(id);
        model.addAttribute("tkToBeUpdated", tkToBeUpdated);
        return "/takenEquipment/updateTakenEquipment";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("takenEquipment", takenEquipmentService.show(id));
        return "/takenEquipment/showTakenEquipment";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("takenEquipment") TakenEquipment takenEquipment) {
        return "/takenEquipment/addTakenEquipment";
    }

    @PostMapping()
    public String create(@ModelAttribute("takenEquipment") TakenEquipment takenEquipment){
        takenEquipmentService.create(takenEquipment);
        return "redirect:/takenequipment";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("takenEquipment") TakenEquipment tkUpdated){
        tkUpdated.setId(id);
        takenEquipmentService.update(tkUpdated);
        return "redirect:/takenequipment";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        takenEquipmentService.delete(id);
        return "redirect:/takenequipment";
    }
}
