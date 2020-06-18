package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.PossibleSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PossibleScheduleService {
    Page<PossibleSchedule> getAllPossibleSchedulesByInscriptionProcessId(Long inscriptionProcessId, Pageable pageable);
    PossibleSchedule createPossibleSchedule(PossibleSchedule possibleSchedule);
    PossibleSchedule updatePossibleSchedule(Long possibleScheduleId, PossibleSchedule possibleSchedule);
    ResponseEntity<?> deletePossibleSchedule(Long possibleScheduleId, Long inscriptionProcessId);
    Page<PossibleSchedule> getAllPossibleSchedules(Pageable pageable);
    PossibleSchedule getByIdAndInscriptionProcessId(Long possibleScheduleId, Long inscriptionProcessId);
}
