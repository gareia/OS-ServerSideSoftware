package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeriodService {

    Period createPeriod(Long educationProviderId, Period period);
    Page<Period> getAllPeriodsByEducationProviderId(Long educationProviderId, Pageable pageable);

    Period getByIdAndEducationProviderId(Long educationProviderId, Long periodId);

}


