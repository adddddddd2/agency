package ru.mirea.adel.agency.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.mirea.adel.agency.db.model.Role;
import ru.mirea.adel.agency.db.model.dto.RoleDto;
import ru.mirea.adel.agency.db.respository.RoleRepository;
import ru.mirea.adel.agency.exception.RoleNotFoundException;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role addRole(RoleDto roleDto) {
        return roleRepository.save(modelMapper.map(roleDto, Role.class));
    }

    public void removeRole(String roleName) {
        roleRepository.delete(
                roleRepository.findByName(roleName)
                .orElseThrow(() -> {throw new RoleNotFoundException("No such role '" + roleName + "'");})
        );
    }
}
