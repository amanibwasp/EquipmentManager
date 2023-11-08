package ru.amanibwasp.EquipmentManager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.amanibwasp.EquipmentManager.models.Equipment;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public HashSet<Equipment> chosen_equipment (){
        return new HashSet<Equipment>();
    }

}
