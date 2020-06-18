package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User createUser (User user);
    User getUserById (Long userId);
    User getUserByIdAndRoleId (Long roleId,Long userId);
    User updateUser (Long userId, User userDetails);
    ResponseEntity<?> deleteUser (Long userId);
    Page<User> getAllUser(Pageable pageable);
}
