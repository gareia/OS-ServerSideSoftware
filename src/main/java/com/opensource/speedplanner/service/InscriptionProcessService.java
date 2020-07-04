package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.InscriptionProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InscriptionProcessService {

    InscriptionProcess createInscriptionProcess(Long userId, InscriptionProcess inscriptionProcess);

    InscriptionProcess getInscriptionProcessByIdAndUserId(Long userId, Long inscriptionProcessId);
    Page<InscriptionProcess> getAllInscriptionProcessesByUserId(Long userId, Pageable pageable);

    /*
    //InscriptionProcess updateInscriptionProcess(Long inscriptionProcessId, InscriptionProcess inscriptionProcessDetails);
    ResponseEntity<?> deleteInscriptionProcess(Long userId, Long inscriptionProcessId);
    */
}
