package ru.amanibwasp.EquipmentManager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name="participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAndEvent {

    @Id
    @GeneratedValue
    private Long id;
    private Long person_id;
    private Long event_id;
}
