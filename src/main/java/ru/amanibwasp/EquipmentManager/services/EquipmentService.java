package ru.amanibwasp.EquipmentManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Equipment;
import ru.amanibwasp.EquipmentManager.repositories.EquipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    @Autowired
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> index() {
        return equipmentRepository.findAll();
    }


    public Optional<Equipment> show(Long id) {
        return equipmentRepository.findById(id);
    }

    public void create(Equipment equipment) { equipmentRepository.save(equipment); }

    public void update(Equipment equipmentUpdated) {
        equipmentRepository.save(equipmentUpdated);
    }

    public void delete(Long id) {
        equipmentRepository.deleteById(id);
    }
}
