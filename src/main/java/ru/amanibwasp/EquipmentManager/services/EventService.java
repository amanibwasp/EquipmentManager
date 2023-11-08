package ru.amanibwasp.EquipmentManager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Event;
import ru.amanibwasp.EquipmentManager.repositories.EventRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final PersonAndEventService personAndEventService;

    public List<Event> index() {
        return eventRepository.findAll();
    }

    public List<Event> findParticipating(Long person_id) {
        return personAndEventService.index().stream()
                .filter(item -> Objects.equals(item.getPerson_id(), person_id))
                .map(item -> show(item.getEvent_id()).get()).toList();
    }

    public Optional<Event> show(Long id) { return eventRepository.findById(id);}

    public void create(Event event) {
        eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }
}
