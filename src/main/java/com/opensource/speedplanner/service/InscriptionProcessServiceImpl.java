package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.InscriptionProcess;
import com.opensource.speedplanner.model.Period;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.PeriodRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InscriptionProcessServiceImpl implements InscriptionProcessService{

    @Autowired
    InscriptionProcessRepository inscriptionProcessRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PeriodRepository periodRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public InscriptionProcess createInscriptionProcess(Long userId, InscriptionProcess inscriptionProcess) {

        return userRepository.findById(userId).map( user -> {
                    inscriptionProcess.setUser(user);
                    Period period = periodRepository.findByCode(user.getProfile()
                            .getEducationProvider().getCurrentPeriodCode());
                    inscriptionProcess.setPeriod(period);
                    inscriptionProcess.setCourses(courseRepository.findAll().stream()
                    .filter(course -> course.getSemester() == user.getProfile().getSemester())
                    .collect(Collectors.toList()));

                    return inscriptionProcessRepository.save(inscriptionProcess);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

    }

    @Override
    public InscriptionProcess getInscriptionProcessByIdAndUserId(Long userId, Long inscriptionProcessId) {
        return inscriptionProcessRepository.findByIdAndUserId(inscriptionProcessId, userId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("InscriptionProcess with Id: %s and User Id: %s not found",
                                inscriptionProcessId, userId)));
    }

    @Override
    public Page<InscriptionProcess> getAllInscriptionProcessesByUserId(Long userId, Pageable pageable) {
        return inscriptionProcessRepository.findByUserId(userId, pageable);
    }

    /*
    @Override
    public ResponseEntity<?> deleteInscriptionProcess(Long userId, Long inscriptionProcessId) {

        InscriptionProcess inscriptionProcess = getInscriptionProcessByIdAndUserId(userId, inscriptionProcessId);
        //throws exception if inscriptionProcessId and userId arent found

        //user.setInscriptionProcess(inscriptionProcess);
        inscriptionProcessRepository.delete(inscriptionProcess);
        return ResponseEntity.ok().build();
    }
    */

}
