package ru.mirea.adel.agency.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.mirea.adel.agency.db.model.Geography;
import ru.mirea.adel.agency.db.model.dto.GeographyDto;
import ru.mirea.adel.agency.db.respository.GeographyRepository;

@Service
public class GeographyService {

    private final GeographyRepository geographyRepository;

    private final ModelMapper modelMapper;

    public GeographyService(GeographyRepository geographyRepository, ModelMapper modelMapper) {
        this.geographyRepository = geographyRepository;
        this.modelMapper = modelMapper;
    }

    public Geography createGeography(GeographyDto geographyDto) {
        return geographyRepository.save(modelMapper.map(geographyDto, Geography.class));
    }


    public void deleteGeography(Integer geographyId) {
        geographyRepository.deleteById(geographyId);
    }
}
