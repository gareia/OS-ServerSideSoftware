package com.opensource.speedplanner.controller;

//import com.opensource.speedplanner.model.PossibleSchedule;
import com.opensource.speedplanner.model.Professor;
import com.opensource.speedplanner.resource.ProfessorResource;
import com.opensource.speedplanner.resource.SaveProfessorResource;
import com.opensource.speedplanner.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="professors", description = "Professors API")
@RestController
@RequestMapping("/api")
public class ProfessorController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfessorService professorService;



    @Operation(summary = "Create Professor", description = "Creates a new professor.", tags = { "professor" })
    @PostMapping("/professors/")
    public ProfessorResource createProfessor(@Valid @RequestBody SaveProfessorResource resource){
        return convertToResource(professorService.createProfessor(convertToEntity(resource)));
    }

    @Operation(summary = "Get Professor By Id", description = "Gets a particular professor, given its" +
            "Id.", tags = { "professors" })
    @GetMapping("/professors/{professorId}")
    public ProfessorResource getProfessorById(@PathVariable(name = "professorId") Long professorId){
        return convertToResource(professorService.getProfessorById(professorId));
    }

    @Operation(summary = "Update Professor", description = "Updates the attributes of a particular " +
            "professor, given its Id and corresponding resource.", tags = { "professors" })
    @PutMapping("/professors/{professorId}")
    public ProfessorResource updateProfessor(@PathVariable(name = "professorId") Long professorId,
                                             @Valid @RequestBody SaveProfessorResource resource){
        return convertToResource(professorService.updateProfessor(professorId, convertToEntity(resource)));
    }

    @Operation(summary = "Get All Professors", description = "Gets every existing professor in pages",
            tags = { "professors" })
    @GetMapping("/professors")
    public Page<ProfessorResource> getAllProfessors(Pageable pageable){
        Page<Professor> professorPage = professorService.getAllProfessors(pageable);
        List<ProfessorResource> resources = professorPage.getContent().stream().map(this::convertToResource).
            collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Delete Professor", description = "Deletes a particular professor, given its Id.",
            tags = { "professors" })
    @DeleteMapping("professors/{professorId}")
    public ResponseEntity<?> deleteProfessor(@PathVariable(name = "professorId") Long professorId){
        return professorService.deleteProfessor(professorId);
    }


 private ProfessorResource convertToResource(Professor entity){
        return mapper.map(entity, ProfessorResource.class);
    }
    private Professor convertToEntity(SaveProfessorResource resource){

        return mapper.map(resource, Professor.class);
    }

}
