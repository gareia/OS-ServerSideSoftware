package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/*
    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);
    Comment getCommentByIdAndPostId(Long postId, Long commentId);
    Comment createComment(Long postId, Comment comment);
    Comment updateComment(Long postId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long postId, Long commentId);*/

public interface RoleService {
    Page<Role> getAllRoles(Pageable pageable);
    Page<Role> getAllRolesByUserId(Long userId , Pageable pageable);
    Role getRoleByIdAndUserId(Long userId , Long roleId);
    Role createRole(Long userId, Role role );
    Role updateRole(Long userId,Long roleId ,Role roleDetails );
    ResponseEntity<?> deleteRole(Long userId, Long roleId);
}
