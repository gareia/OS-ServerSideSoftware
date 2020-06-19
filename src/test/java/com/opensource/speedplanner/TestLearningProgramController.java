package com.opensource.speedplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.speedplanner.controller.LearningProgramController;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LearningProgramController.class)
public class TestLearningProgramController {

    @Autowired
    private MockMvc mvc;

    //GET ALL
    @Test
    public void givenLearningPrograms_whenGetAllLearningPrograms_thenReturnJsonArray()
            throws Exception{
        mvc.perform( MockMvcRequestBuilders
                .get("/api/educationProviders/{educationProviderId}/learningPrograms", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    //GET BY ID
    @Test
    public void givenLearningProgram_whenGetLearningProgramById_thenReturnOkStatus()
            throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/educationProviders/{educationProviderId}/learningPrograms/{learningProgramId}", 1, 8)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    //POST
    @Test
    public void givenSaveLearningProgramResource_whenPostLearningProgram_thenReturnCreatedStatus()
            throws Exception{

        String resource = "{\"type\":Carrera universitaria, \"name\":Ingenieria de software, \"numberOfCourse\":34}]";

        mvc.perform(MockMvcRequestBuilders
                .post("/api/educationProviders/{educationProviderId}/learningPrograms",1)
                .content(asJsonString(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    //UPDATE
    @Test
    public void givenSaveLearningProgramResource_whenPutLearningProgram_thenReturnOkStatus()
            throws Exception{

        String resource = "{\"type\":Carrera universitaria, \"name\":Ingenieria civil, \"numberOfCourse\":30}]";
        mvc.perform(MockMvcRequestBuilders
                .put("/api/educationProviders/{educationProviderId}/learningPrograms/{learningProgramId}", 1, 8)
                .content(resource)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //DELETE
}
