package com.opensource.speedplanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.speedplanner.controller.EducationProviderController;
import com.opensource.speedplanner.resource.EducationProviderResource;
import com.opensource.speedplanner.resource.SaveEducationProviderResource;
import com.opensource.speedplanner.service.EducationProviderService;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EducationProviderController.class)
public class TestEducationProviderController {

    //@InjectMocks
    //EducationProviderController educationProviderController;

    @Autowired
    private MockMvc mvc;

    //GET ALL
    @Test
    public void givenEducationProviders_whenGetAllEducationProviders_thenReturnJsonArray()
            throws Exception{
        mvc.perform( MockMvcRequestBuilders
                .get("/api/educationProviders")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.educationProviders").exists());
    }
    //GET BY ID
    @Test
    public void givenEducationProvider_whenGetEducationProviderById_thenReturnOkStatus()
            throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/educationProviders/{educationProviderId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
    //POST
    @Test
    public void givenSaveEducationProviderResource_whenPostEducationProvider_thenReturnCreatedStatus()
            throws Exception{

        String resource = "{\"name\":UPC, \"numberOfCareers\":37}]";
        /*
        SaveEducationProviderResource saveEducationProviderResource = new SaveEducationProviderResource();
        saveEducationProviderResource.setName("UPC");
        saveEducationProviderResource.setNumberOfCareers(37);*/

        mvc.perform(MockMvcRequestBuilders
                .post("/api/educationProviders")
                .content(asJsonString(resource))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    //UPDATE
    @Test
    public void givenSaveEducationProviderResource_whenPutEducationProvider_thenReturnOkStatus()
        throws Exception{

        String resource = "{\"name\":UPN, \"numberOfCareers\":37}]";
        mvc.perform(MockMvcRequestBuilders
                .put("/api/educationProviders/{educationProviderId}", 1)
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
