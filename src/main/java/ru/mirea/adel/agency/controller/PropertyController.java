package ru.mirea.adel.agency.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.adel.agency.db.model.Property;
import ru.mirea.adel.agency.db.model.dto.PropertyDto;
import ru.mirea.adel.agency.db.model.dto.PropertyRequest;
import ru.mirea.adel.agency.service.PropertyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/property")
public class PropertyController {

    private final PropertyService propertyService;

    private final ModelMapper modelMapper;

    public PropertyController(PropertyService propertyService, ModelMapper modelMapper) {
        this.propertyService = propertyService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public PropertyDto createProperty(@RequestBody PropertyRequest propertyRequest) {
        return convertPropertyToDto(propertyService.createProperty(propertyRequest));
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<String> deleteProperty(@PathVariable(name = "propertyId") Integer propertyId) {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping
    public List<PropertyDto> findAll(@RequestParam(name = "property_type", required = false) String propertyType,
                                     @RequestParam(name = "owner", required = false) String ownerUsername) {
        return convertPropertyToDto(propertyService.findAll(propertyType, ownerUsername));
    }

    public List<PropertyDto> convertPropertyToDto(List<Property> properties) {
        return properties.stream()
                .map(element -> modelMapper.map(element, PropertyDto.class))
                .collect(Collectors.toList());
    }

    public PropertyDto convertPropertyToDto(Property property) {
        return modelMapper.map(property, PropertyDto.class);
    }
}
