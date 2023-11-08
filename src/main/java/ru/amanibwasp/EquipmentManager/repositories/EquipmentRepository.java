package ru.amanibwasp.EquipmentManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amanibwasp.EquipmentManager.models.Equipment;

@Repository
public interface EquipmentRepository  extends JpaRepository<Equipment, Long> {
}
