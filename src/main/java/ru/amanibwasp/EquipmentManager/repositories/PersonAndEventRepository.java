package ru.amanibwasp.EquipmentManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.amanibwasp.EquipmentManager.models.PersonAndEvent;

public interface PersonAndEventRepository extends JpaRepository<PersonAndEvent, Long> {
}
