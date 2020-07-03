package com.opensource.speedplanner.service;


import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Period;
import com.opensource.speedplanner.repository.EducationProviderRepository;
import com.opensource.speedplanner.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private EducationProviderRepository educationProviderRepository;


    @Override
    public Period createPeriod(Long educationProviderId, Period period) {
        return educationProviderRepository.findById(educationProviderId).map(educationProvider -> {
            period.setEducationProvider(educationProvider);
            return periodRepository.save(period);
        }).orElseThrow(() -> new ResourceNotFoundException("Education Provider", "Id", educationProviderId));
    }

    @Override
    public Page<Period> getAllPeriodsByEducationProviderId(Long educationProviderId, Pageable pageable) {
        return periodRepository.findByEducationProviderId(educationProviderId, pageable);
    }

    @Override
    public Period getByIdAndEducationProviderId(Long educationProviderId, Long periodId) {
        return periodRepository.findByIdAndEducationProviderId(periodId, educationProviderId).
                orElseThrow(() -> new ResourceNotFoundException(String.format("Education provider with id: %s " +
                        "and period id: %s", educationProviderId, periodId)));
    }

}
