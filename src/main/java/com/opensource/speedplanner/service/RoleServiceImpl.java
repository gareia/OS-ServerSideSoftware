package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Role;
import com.opensource.speedplanner.repository.RoleRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return  roleRepository.findAll(pageable);
    }

    @Override
    public Page<Role> getAllRolesByUserId(Long userId, Pageable pageable) {
        return  roleRepository.findByUserId(userId , pageable);
    }

    @Override
    public Role getRoleByIdAndUserId(Long userId, Long roleId) {
        return roleRepository.findByIdAndUserId(userId , roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role Not found with Id" + roleId
                        +" and userId" + userId));
    }

    @Override
    public Role createRole(Long userId, Role role) {
        return userRepository.findById(userId).map( user -> {
                     return roleRepository.save(role);
                }).orElseThrow(() -> new ResourceNotFoundException("User" , "Id" , "userId"));
    }

    @Override
    public Role updateRole(Long userId, Long roleId, Role roleDetails)
    {
        if(!userRepository.existsById(userId))
            throw  new ResourceNotFoundException("User" , "Id" , "userId");

        return roleRepository.findById(roleId).map(role -> {
                role.setType(roleDetails.getType());
                return roleRepository.save(role);
                }).orElseThrow(() ->new ResourceNotFoundException("Role" , "Id" , "roleId"));
    }

    @Override
    public ResponseEntity<?> deleteRole(Long userId, Long roleId) {
        return roleRepository.findByIdAndUserId(userId , roleId).map(role -> {
            roleRepository.delete(role);
            return  ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Role not found with Id" + roleId + " and userId" + userId));
    }
}
