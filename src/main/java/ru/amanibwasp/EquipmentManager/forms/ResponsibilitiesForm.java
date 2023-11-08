package ru.amanibwasp.EquipmentManager.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.amanibwasp.EquipmentManager.models.Equipment;
import ru.amanibwasp.EquipmentManager.models.Person;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibilitiesForm {
    private List<Equipment> equipmentList;
    private List<Person> people;
}
