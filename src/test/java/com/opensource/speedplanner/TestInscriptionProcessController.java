package com.opensource.speedplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.speedplanner.controller.InscriptionProcessController;
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
@WebMvcTest(controllers = InscriptionProcessController.class)
public class TestInscriptionProcessController {
    @Autowired
    private MockMvc mvc;

    //GET ALL
    @Test
    public void givenInscriptionProcesses_whenGetAllInscriptionProcesses_thenReturnJsonArray()
            throws Exception{
        mvc.perform( MockMvcRequestBuilders
                .get("/api/users/{userId}/inscriptionProcesses", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    //GET BY ID
    @Test
    public void givenInscriptionProcess_whenGetInscriptionProcessById_thenReturnOkStatus()
            throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/users/{userId}/inscriptionProcesses/{inscriptionProcessId}", 1, 8)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    //POST
    @Test
    public void givenSaveInscriptionProcessResource_whenPostInscriptionProcess_thenReturnCreatedStatus()
            throws Exception{

        String resource = "{}]";

        mvc.perform(MockMvcRequestBuilders
                .post("/api/users/{userId}/inscriptionProcesses",1)
                .content(asJsonString(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    // NO HAY UPDATE

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //DELETE
}
