package ru.mirea.adel.agency.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.adel.agency.db.model.Role;
import ru.mirea.adel.agency.db.model.dto.RoleDto;
import ru.mirea.adel.agency.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/role")
public class RoleController {

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    public RoleController(ModelMapper modelMapper, RoleService roleService) {
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDto> findAll() {
        return convertRoleToDto(roleService.findAll());
    }

    @PostMapping
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        return convertRoleToDto(roleService.addRole(roleDto));
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<String> createRole(@PathVariable(name = "roleName") String roleName) {
        roleService.removeRole(roleName);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public List<RoleDto> convertRoleToDto(List<Role> roles) {
        return roles.stream()
                .map(element -> modelMapper.map(element, RoleDto.class))
                .collect(Collectors.toList());
    }

    public RoleDto convertRoleToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }
}
