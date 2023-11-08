package ru.amanibwasp.EquipmentManager.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Equipment;
import ru.amanibwasp.EquipmentManager.models.TakenEquipment;
import ru.amanibwasp.EquipmentManager.repositories.TakenEquipmentRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TakenEquipmentService {
    private final TakenEquipmentRepository takenEquipmentRepository;
    private final EquipmentService equipmentService;



    public List<TakenEquipment> index() {
        return takenEquipmentRepository.findAll();
    }

    public List<Equipment> findChosenEquipment(Long event_id){
        List<TakenEquipment> all_taken_equipment_rows = index();
        List<Long> chosen_equipment_id = all_taken_equipment_rows.stream()
                .map(TakenEquipment::getEquipment_id)
                .toList();
        return chosen_equipment_id.stream()
                .map(id -> equipmentService.show(id).get())
                .toList();
    }

    public Optional<TakenEquipment> show(Long id) {
        return takenEquipmentRepository.findById(id);
    }

    public void create(TakenEquipment takenEquipment) { takenEquipmentRepository.save( takenEquipment);}

    public void update(TakenEquipment takenEquipmentUpdated) { takenEquipmentRepository.save(takenEquipmentUpdated);}

    public void delete(Long id) { takenEquipmentRepository.deleteById(id);}
}
