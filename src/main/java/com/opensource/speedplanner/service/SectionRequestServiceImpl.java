package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.InscriptionProcess;
import com.opensource.speedplanner.model.SectionRequest;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.SectionRequestRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SectionRequestServiceImpl implements  SectionRequestService  {

    @Autowired
    private SectionRequestRepository sectionRequestRepository;

    @Autowired
    private InscriptionProcessRepository inscriptionProcessRepository;




    @Override
    public Page<SectionRequest> getAllSectionRequests(Pageable pageable) {
        return sectionRequestRepository.findAll(pageable);
    }

    @Override
    public Page<SectionRequest> getAllSectionRequestByInscriptionProcessId(Long inscriptionProcessId, Pageable pageable) {
        return sectionRequestRepository.findByInscriptionProcessId(inscriptionProcessId , pageable);
    }

    @Override
    public SectionRequest getSectionRequestByIdAndInscriptionProcessId(Long inscriptionProcessId, Long sectionRequestId) {
        return sectionRequestRepository.findByIdAndInscriptionProcessId(inscriptionProcessId , sectionRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with Id"
                        + sectionRequestId + " and inscriptionProcessId " + inscriptionProcessId));
    }

    @Override
    public SectionRequest createSectionRequest(Long inscriptionProcessId, SectionRequest sectionRequest) {
        return inscriptionProcessRepository.findById(inscriptionProcessId).map(inscriptionProcess -> {
            return sectionRequestRepository.save(sectionRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("InscriptionProcessId", "Id" , "inscriptionProcessId"));
    }

    @Override
    public SectionRequest updateSectionRequest(Long inscriptionProcessId, Long sectionRequestId, SectionRequest sectionRequestDetails) {
        if(!inscriptionProcessRepository.existsById(inscriptionProcessId))
            throw new ResourceNotFoundException("InscriptionProcessId", "Id" , "inscriptionProcessId");
        return sectionRequestRepository.findById(sectionRequestId).map(sectionRequest ->{
            sectionRequest.setMessage(sectionRequestDetails.getMessage());
            return sectionRequestRepository.save(sectionRequest);
        } ).orElseThrow(() -> new ResourceNotFoundException("SectionRequest" , "Id" , "sectionRequestId"));
    }

    @Override
    public ResponseEntity<?> deleteSectionRequest(Long inscriptionProcessId, Long sectionRequestId) {
        return sectionRequestRepository.findByIdAndInscriptionProcessId(inscriptionProcessId , sectionRequestId).map(sectionRequest -> {
            sectionRequestRepository.delete(sectionRequest);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
           "SectionRequest not found Id" + sectionRequestId + "and InscriptionProcessId "  + inscriptionProcessId
        ));
    }
}
