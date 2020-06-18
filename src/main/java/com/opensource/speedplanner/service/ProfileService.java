package com.opensource.speedplanner.service;
/*
    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);
    Comment getCommentByIdAndPostId(Long postId, Long commentId);
    Comment createComment(Long postId, Comment comment);
    Comment updateComment(Long postId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long postId, Long commentId);*/


import com.opensource.speedplanner.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Page<Profile> getAllProfiles(Pageable pageable);
    Page<Profile> getAllProfilesByUserId(Long userId , Pageable pageable);
    Profile createProfile(Long userId, Profile profile);
    Profile updateProfile(Long userId , Long profileId, Profile profileDetails);
    ResponseEntity<?> deleteProfile(Long postId, Long profileId);
}