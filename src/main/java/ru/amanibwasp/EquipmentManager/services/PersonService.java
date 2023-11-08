package ru.amanibwasp.EquipmentManager.services;


import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.amanibwasp.EquipmentManager.models.Person;
import ru.amanibwasp.EquipmentManager.models.Role;
import ru.amanibwasp.EquipmentManager.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Optional<Person> show(Long id) {
        return personRepository.findById(id);
    }

    public void create(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    public void update(Person personUpdated) {
        personRepository.save(personUpdated);
    }

    public List<Person> findGuests() {
        return personRepository.findAll().stream()
                .filter(person -> person.getRole() == Role.ROLE_GUEST)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByEmail(email);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return person.get();
    }
}
