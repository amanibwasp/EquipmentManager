package ru.amanibwasp.EquipmentManager.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amanibwasp.EquipmentManager.models.TakenEquipment;

@Repository
public interface TakenEquipmentRepository  extends JpaRepository<TakenEquipment, Long> {
}
