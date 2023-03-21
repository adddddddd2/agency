package ru.mirea.adel.agency.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.mirea.adel.agency.db.model.Role;
import ru.mirea.adel.agency.db.model.User;
import ru.mirea.adel.agency.db.model.dto.CreateUserRequest;
import ru.mirea.adel.agency.db.respository.RoleRepository;
import ru.mirea.adel.agency.db.respository.UserRepository;
import ru.mirea.adel.agency.exception.RoleNotFoundException;
import ru.mirea.adel.agency.exception.UserNotFoundException;
import ru.mirea.adel.agency.exception.UsernameAlreadyTakenException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("There is no user having username '" + username + "'");
        }

        return userOpt.get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(CreateUserRequest user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
        if (userOpt.isPresent()) {
            throw new UsernameAlreadyTakenException("Username '" + user.getUsername() + "' is already taken");
        }
        return userRepository.save(modelMapper.map(user, User.class));
    }

    public User updateUser(CreateUserRequest user) {
        Optional<User> userOpt = userRepository.findByUsername(user.getUsername());
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("There is no user having username '" + user.getUsername() + "'");
        }
        User userToUpdate = userOpt.get();
        User userSource = modelMapper.map(user, User.class);
        userSource.setRoles(userToUpdate.getRoles());
        modelMapper.map(userSource, userToUpdate);
        userRepository.save(userToUpdate);
        return userToUpdate;
    }
    
    
    public User addRoleToUser(String username, String rolename) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("There is no user having username '" + username + "'");
        }
        Optional<Role> roleOpt = roleRepository.findByName(rolename);
        if (roleOpt.isEmpty()) {
            throw new RoleNotFoundException("There is no such role '" + rolename + "'");
        }
        User user = userOpt.get();
        user.addRole(roleOpt.get());
        return userRepository.save(user);
    }
}
