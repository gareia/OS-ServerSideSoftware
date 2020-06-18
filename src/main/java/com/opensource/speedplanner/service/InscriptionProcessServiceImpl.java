package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.InscriptionProcess;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InscriptionProcessServiceImpl implements InscriptionProcessService{

    @Autowired
    InscriptionProcessRepository inscriptionProcessRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public InscriptionProcess createInscriptionProcess(Long userId, InscriptionProcess inscriptionProcess) {
        return userRepository.findById(userId).map( user -> {
                    inscriptionProcess.setUser(user);
                    user.setInscriptionProcess(inscriptionProcess);
                    return inscriptionProcessRepository.save(inscriptionProcess);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

    }

    @Override
    public InscriptionProcess getInscriptionProcessByIdAndUserId(Long userId, Long inscriptionProcessId) {
        return inscriptionProcessRepository.findByIdAndUserId(userId, inscriptionProcessId)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("InscriptionProcess with Id: %s and UserId: %s not found", inscriptionProcessId,
                                userId)));
    }

    @Override
    public Page<InscriptionProcess> getAllInscriptionProcessesByUserId(Long userId, Pageable pageable) {
        return inscriptionProcessRepository.findByUserId(userId, pageable);
    }

    @Override
    public ResponseEntity<?> deleteInscriptionProcess(Long userId, Long inscriptionProcessId) {

        InscriptionProcess inscriptionProcess = getInscriptionProcessByIdAndUserId(userId, inscriptionProcessId);
        //throws exception if inscriptionProcessId and userId arent found

        //user.setInscriptionProcess(inscriptionProcess);
        inscriptionProcessRepository.delete(inscriptionProcess);
        return ResponseEntity.ok().build();
    }


}
