package ru.amanibwasp.EquipmentManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TakenEquipment {
    @Id
    @GeneratedValue
    private Long id;
    private Long person_id;
    private Long equipment_id;
    private Long event_id;
    private int cnt = 0;

}
