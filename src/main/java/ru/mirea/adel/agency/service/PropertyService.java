package ru.mirea.adel.agency.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.mirea.adel.agency.db.model.Geography;
import ru.mirea.adel.agency.db.model.Property;
import ru.mirea.adel.agency.db.model.PropertyType;
import ru.mirea.adel.agency.db.model.User;
import ru.mirea.adel.agency.db.model.dto.PropertyRequest;
import ru.mirea.adel.agency.db.respository.PropertyRepository;
import ru.mirea.adel.agency.db.respository.PropertyTypeRepository;
import ru.mirea.adel.agency.db.respository.UserRepository;
import ru.mirea.adel.agency.exception.PropertyNotExistsException;
import ru.mirea.adel.agency.exception.UsernameNotFoundException;
import ru.mirea.adel.agency.exception.WrongPropertyTypeException;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyTypeRepository propertyTypeRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final GeographyService geographyService;

    public PropertyService(PropertyRepository propertyRepository,
                           PropertyTypeRepository propertyTypeRepository,
                           UserRepository userRepository, ModelMapper modelMapper,
                           GeographyService geographyService) {
        this.propertyRepository = propertyRepository;
        this.propertyTypeRepository = propertyTypeRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.geographyService = geographyService;
    }

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property createProperty(PropertyRequest propertyRequest) {
        Optional<PropertyType> propertyTypeOpt = propertyTypeRepository.findById(propertyRequest.getPropertyTypeName());
        if (propertyTypeOpt.isEmpty()) {
            throw new WrongPropertyTypeException("type '" + propertyRequest.getPropertyTypeName() + "' doesn't exist");
        }
        Optional<User> userOpt = userRepository.findByUsername(propertyRequest.getOwner());
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("There is no user having username '" + propertyRequest.getOwner() + "'");
        }
        Geography geography = geographyService.createGeography(propertyRequest.getGeography());
        Property property = modelMapper.map(propertyRequest, Property.class);
        property.setGeography(geography);
        property.setPropertyType(propertyTypeOpt.get());
        property.setOwner(userOpt.get());
        return propertyRepository.save(property);
    }

    public void deleteProperty(Integer propertyId) {
        Optional<Property> propertyOpt = propertyRepository.findById(propertyId);
        if (propertyOpt.isEmpty()) {
            throw new PropertyNotExistsException("Wrong property id: " + propertyId);
        }
        geographyService.deleteGeography(propertyOpt.get().getGeography().getId());
        propertyRepository.deleteById(propertyId);
    }

    public List<Property> findAll(String propertyType, String ownerUsername) {
        if (propertyType != null) {
            if (ownerUsername != null) {
                return propertyRepository.findByOwnerUsernameAndPropertyTypeName(ownerUsername, propertyType);
            } else {
                return propertyRepository.findByPropertyTypeName(propertyType);
            }
        } else {
            if (ownerUsername != null) {
                return propertyRepository.findByOwnerUsername(ownerUsername);
            }
            return propertyRepository.findAll();
        }
    }
}
