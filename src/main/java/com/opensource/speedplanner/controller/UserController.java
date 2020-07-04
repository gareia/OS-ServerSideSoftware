package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.User;
import com.opensource.speedplanner.resource.SaveUserResource;
import com.opensource.speedplanner.resource.UserResource;
import com.opensource.speedplanner.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="users", description = "Users API")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;



    @Operation(summary = "Create User", description = "Create User by given resource", tags = {"users"})
    @PostMapping("profiles/{profileId}/users")
    public UserResource createUser(@PathVariable Long profileId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.createUser(profileId, convertToEntity(resource)));
    }

    @Operation(summary = "Get all Users", description = "Get All Users by Pages", tags = {"users"})
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable) {
        List<UserResource> users = userService.getAllUsers(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int usersCount = users.size();
        return new PageImpl<>(users, pageable, usersCount);
    }

    @Operation(summary = "Get User by Id", description = "Get User by specifying Id", tags = {"users"})
    @GetMapping ("/users/{id}")
    public UserResource getUserById(@PathVariable(name = "id") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }


    //blubli
/*
    @Operation(summary = "Update User", description = "Update User by specifying Id and given resource", tags = {"users"})
    @PutMapping("profiles/{profileId}/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete User", description = "Delete User by specifying Id", tags = {"users"})
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        return userService.deleteUser(userId);
    }*/


    private User convertToEntity(SaveUserResource resource) { return mapper.map(resource, User.class); }

    private UserResource convertToResource(User entity) { return mapper.map(entity, UserResource.class); }
}
