package ru.mirea.adel.agency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.adel.agency.db.model.PropertyType;
import ru.mirea.adel.agency.service.PropertyTypeService;

import java.util.List;

@RestController
@RequestMapping("/v1/propertyType")
public class PropertyTypeController {

    private final PropertyTypeService propertyTypeService;

    public PropertyTypeController(PropertyTypeService propertyTypeService) {
        this.propertyTypeService = propertyTypeService;
    }

    @GetMapping
    public List<PropertyType> findAll() {
        return propertyTypeService.findAll();
    }
}
