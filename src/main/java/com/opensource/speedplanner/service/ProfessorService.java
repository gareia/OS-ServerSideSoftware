package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfessorService {
    Professor createProfessor(Professor professor);
    Professor getProfessorById(Long professorId);
    Professor updateProfessor(Long professorId, Professor professor);
    ResponseEntity<?> deleteProfessor(Long professorId);
    Page<Professor> getAllProfessors(Pageable pageable);
}
