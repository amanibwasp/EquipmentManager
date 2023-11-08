package ru.amanibwasp.EquipmentManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    @Transient
    private HashSet<Person> participants;
    @Transient
    private HashSet<Equipment> chosen_equipment;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date event_date;
}
