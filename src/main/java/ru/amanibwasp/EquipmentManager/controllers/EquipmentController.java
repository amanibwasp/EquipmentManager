package ru.amanibwasp.EquipmentManager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.amanibwasp.EquipmentManager.services.EquipmentService;
import ru.amanibwasp.EquipmentManager.models.Equipment;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public String index(Model model) {
        List<Equipment> equipmentList = equipmentService.index();
        model.addAttribute("equipmentList", equipmentList);
        return "/equipment/allEquipment";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model){
        Optional<Equipment> equipmentToBeUpdated = equipmentService.show(id);
        model.addAttribute("equipment", equipmentToBeUpdated);
        return "/equipment/updateEquipment";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model){
        model.addAttribute("equipment", equipmentService.show(id));
        return "/equipment/showEquipment";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("equipment") Equipment equipment) {
        return "/equipment/addEquipment";
    }

    @PostMapping
    public String create(@ModelAttribute("equipment") Equipment equipment){
        equipmentService.create(equipment);
        return "redirect:/equipment";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("equipment") Equipment equipmentUpdated){
        equipmentUpdated.setId(id);
        equipmentService.update(equipmentUpdated);
        return "redirect:/equipment";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        equipmentService.delete(id);
        return "redirect:/equipment";
    }
}
