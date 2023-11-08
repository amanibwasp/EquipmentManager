package ru.amanibwasp.EquipmentManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Event;
import ru.amanibwasp.EquipmentManager.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> index() {
        return eventRepository.findAll();
    }

    public Optional<Event> show(Long id) { return eventRepository.findById(id);}

    public void create(Event event) {
        eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
