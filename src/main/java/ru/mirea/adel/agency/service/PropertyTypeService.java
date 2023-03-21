package ru.mirea.adel.agency.service;

import org.springframework.stereotype.Service;
import ru.mirea.adel.agency.db.model.PropertyType;
import ru.mirea.adel.agency.db.respository.PropertyTypeRepository;

import java.util.List;

@Service
public class PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    public PropertyTypeService(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    public List<PropertyType> findAll() {
        return propertyTypeRepository.findAll();
    }
}
