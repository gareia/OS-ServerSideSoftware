package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Role;
import com.opensource.speedplanner.resource.RoleResource;
import com.opensource.speedplanner.resource.SaveRoleResource;
import com.opensource.speedplanner.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "roles", description = "Roles API")
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Get User roles" , description = "Get all roles ",
             tags = {"roles"})
    @GetMapping("/roles")
    public Page<RoleResource> getAllRoles(Pageable pageable){
        Page<Role> roles = roleService.getAllRoles(pageable);
        List<RoleResource> resources = roles.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Create Role", description = "Create a Role by User Id and given resource", tags = {"roles"})
    @PostMapping("/users/{userId}/roles")
    public RoleResource createRole(@PathVariable(name = "userId")Long userId ,
                                   @Valid @RequestBody SaveRoleResource resource){
        return convertToResource(roleService.createRole(userId , convertToEntity(resource)));
    }

    @Operation(summary = "Update Role", description = "Update a Role by specifying Id, User Id and given resource",
            tags = {"roles"})
    @PutMapping("/users/{userId}/roles/{roleId}")
    public RoleResource updateRole(@PathVariable(name = "userId") Long userId ,
                                   @PathVariable(name = "roleId")   Long roleId,
                                   @Valid @RequestBody SaveRoleResource resource){
        return convertToResource(roleService.updateRole(userId,roleId , convertToEntity(resource)));
    }

    @Operation(summary = "Delete Role", description = "Delete a Role by specifying Id and User Id", tags = {"roles"})
    @DeleteMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "userId")Long userId,
                                        @PathVariable(name = "roleId")Long roleId
                                        ){
        return roleService.deleteRole(userId, roleId);
    }

    private Role convertToEntity(SaveRoleResource resource){
        return  mapper.map(resource , Role.class);
    }

    private  RoleResource convertToResource(Role entity){
        return mapper.map(entity , RoleResource.class);
    }
}
