package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.SectionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/*
    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);
    Comment getCommentByIdAndPostId(Long postId, Long commentId);
    Comment createComment(Long postId, Comment comment);
    Comment updateComment(Long postId, Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long postId, Long commentId);*/


public interface SectionRequestService {
    Page<SectionRequest> getAllSectionRequests(Pageable pageable);
    Page<SectionRequest> getAllSectionRequestByInscriptionProcessId(Long inscriptionProcessId , Pageable pageable);
    SectionRequest getSectionRequestByIdAndInscriptionProcessId(Long inscriptionProcessId , Long sectionRequestId );
    SectionRequest createSectionRequest(Long inscriptionProcessId , SectionRequest sectionRequest);
    SectionRequest updateSectionRequest(Long inscriptionProcessId, Long sectionRequestId , SectionRequest sectionRequest);
    ResponseEntity<?> deleteSectionRequest(Long inscriptionProcessId , Long sectionRequestId );
}
