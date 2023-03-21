package ru.mirea.adel.agency.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.adel.agency.db.model.PropertyType;
import ru.mirea.adel.agency.db.model.Role;
import ru.mirea.adel.agency.db.respository.PropertyTypeRepository;
import ru.mirea.adel.agency.db.respository.RoleRepository;
import ru.mirea.adel.agency.db.respository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    public ApplicationRunner dataLoader(PropertyTypeRepository propertyTypeRepository,
                                        RoleRepository roleRepository,
                                        UserRepository userRepository) {
        return args -> {
            List<PropertyType> propertyTypes = Arrays.asList(
                    new PropertyType("Комната"),
                    new PropertyType("Квартира"),
                    new PropertyType("Дом"),
                    new PropertyType("Земельный участок"),
                    new PropertyType("Гараж"),
                    new PropertyType("Коммерч. площадь")
            );
            for (PropertyType type : propertyTypes) {
                propertyTypeRepository.save(type);
            }

            List<Role> roles = Arrays.asList(
                    new Role(1, "Продавец"),
                    new Role(2, "Разработчик")
            );
            for (Role role : roles) {
                roleRepository.save(role);
            }
        };
    }
}
