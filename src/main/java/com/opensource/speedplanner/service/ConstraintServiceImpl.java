package com.opensource.speedplanner.service;
/*
import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Constraint;
import com.opensource.speedplanner.repository.ConstraintRepository;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstraintServiceImpl implements ConstraintService{

    @Autowired
    ConstraintRepository constraintRepository;
    @Autowired
    InscriptionProcessRepository inscriptionProcessRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public Constraint createConstraint(Long userId, Long courseId, Constraint constraint) {
        return userRepository.findById(userId).map(user -> {
            user.getInscriptionProcess().get
        setConstraint(constraint);
            return constraintRepository.save(constraint);
        }).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }

    public Constraint getConstraintByUserId(Long userId){
        return userRepository.findById(userId)
                .map(user -> user.getInscriptionProcess().getConstraint())
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Constraint updateConstraint(Long userId, Constraint details) {

        return userRepository.findById(userId).map(user -> {

            Constraint constraint = user.getInscriptionProcess().getConstraint();
            constraint.setStartTime(details.getStartTime());
            constraint.setNumberOfHours(details.getNumberOfHours());
            constraint.setProfessorName(details.getProfessorName());

            return constraintRepository.save(constraint);

        }).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
    }
}
*/