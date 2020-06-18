package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PeriodService {

    Period createPeriod(Long learningProgramId, Period period);
    Period updatePeriod(Long periodId, Long learningProgramId, Period period);
    ResponseEntity<?> deletePeriod(Long periodId, Long learningProgramId);
    Page<Period> getAllPeriods(Pageable pageable);
    Page<Period> getAllPeriodsByLearningProgramId(Long learningProgramId, Pageable pageable);
    Period getByIdAndLearningProgramId(Long periodId, Long learningProgramId);
}
