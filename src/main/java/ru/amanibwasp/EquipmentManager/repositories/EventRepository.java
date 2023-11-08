package ru.amanibwasp.EquipmentManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.amanibwasp.EquipmentManager.models.Event;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {
}
