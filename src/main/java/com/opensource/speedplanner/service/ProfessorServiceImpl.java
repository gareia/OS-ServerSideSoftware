package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Professor;
import com.opensource.speedplanner.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    @Override
    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).
                orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));
    }

    @Override
    public Professor updateProfessor(Long professorId, Professor professorDetails) {
        return professorRepository.findById(professorId).map(professor -> {
            professor.setCode(professorDetails.getCode());
            professor.setNames(professorDetails.getNames());
            professor.setLastNames(professorDetails.getLastNames());
            
            return professorRepository.save(professor);
        }).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));

    }

    @Override
    public ResponseEntity<?> deleteProfessor(Long professorId) {
        return professorRepository.findById(professorId).map(professor -> {
            professorRepository.delete(professor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Professor", "Id", professorId));
    }

    @Override
    public Page<Professor> getAllProfessors(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }
}