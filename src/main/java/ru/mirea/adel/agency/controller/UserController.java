package ru.mirea.adel.agency.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.adel.agency.db.model.User;
import ru.mirea.adel.agency.db.model.dto.CreateUserRequest;
import ru.mirea.adel.agency.db.model.dto.UserDto;
import ru.mirea.adel.agency.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDto> getAll() {
        return convertUserToDto(userService.findAll());
    }

    @PostMapping()
    public UserDto createUser(@RequestBody CreateUserRequest user) {
        User result = userService.createUser(user);
        return convertUserToDto(result);
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable(name = "username") String username) {
        return convertUserToDto(userService.getUserByUsername(username));
    }

    @PutMapping("/{username}")
    public UserDto updateUser(@RequestBody CreateUserRequest user) {
        User result = userService.updateUser(user);
        return convertUserToDto(result);
    }

    @PostMapping("/{username}/role/{roleName}")
    public UserDto addRoleToUser(@PathVariable(name = "username") String username,
                                 @PathVariable(name = "roleName") String roleName){
        return convertUserToDto(userService.addRoleToUser(username, roleName));
    }

    public List<UserDto> convertUserToDto(List<User> users) {
        return users.stream()
                .map(element -> modelMapper.map(element, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
