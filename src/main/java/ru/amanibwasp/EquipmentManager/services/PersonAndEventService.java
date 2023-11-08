package ru.amanibwasp.EquipmentManager.services;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Person;
import ru.amanibwasp.EquipmentManager.models.PersonAndEvent;
import ru.amanibwasp.EquipmentManager.repositories.PersonAndEventRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonAndEventService {

    private final PersonService personService;

    private final PersonAndEventRepository personAndEventRepository;

    public List<PersonAndEvent> index() {
        return personAndEventRepository.findAll();
    }


    public Optional<PersonAndEvent> show(Long id) {
        return personAndEventRepository.findById(id);
    }

    public void create(PersonAndEvent personAndEvent) {
        personAndEventRepository.save(personAndEvent);
    }

    public void update(PersonAndEvent personAndEventUpdated) {
        personAndEventRepository.save(personAndEventUpdated);
    }

    public Set<Person> findParticipants(Long event_id){
        List<PersonAndEvent> all_rows = index();
        List<Long> people_id = all_rows.stream()
                .filter(personAndEvent -> Objects.equals(personAndEvent.getEvent_id(), event_id))
                .map(PersonAndEvent::getPerson_id)
                .toList();
        return people_id.stream()
                .map(id -> personService.show(id).get())
                .collect(Collectors.toSet());
    }

    public void delete(Long id) {
        personAndEventRepository.deleteById(id);
    }
}
