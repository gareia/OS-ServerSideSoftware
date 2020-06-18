package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Section;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

/*
    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);
    Comment getCommentByIdAndPostId(Long postId, Long commentId);
    Comment createComment(Long postId, Comment comment);
    Comment updateComment(Long postId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long postId, Long commentId);*/

public interface SectionService {
    Page<Section> getAllSections(Pageable pageable);
    Page<Section> getAllSectionsByCourseId (Long courseId , Pageable pageable);
    Section getSectionByIdAndCourseId (Long courseId , Long sectionId);
    Section createSection(Long courseId , Section section);
    Section updateSection(Long courseId , Long sectionId , Section section);
    ResponseEntity<?> deleteSection(Long courseId , Long sectionId );
}
