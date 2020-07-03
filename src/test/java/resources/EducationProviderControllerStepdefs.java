package resources;
/*
import com.github.tomakehurst.wiremock.WireMockServer;
import com.opensource.speedplanner.controller.EducationProviderController;
import com.opensource.speedplanner.resource.EducationProviderResource;
import com.opensource.speedplanner.resource.SaveEducationProviderResource;
import com.opensource.speedplanner.service.EducationProviderService;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EducationProviderController.class)
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "classpath: resources")
public class EducationProviderControllerStepdefs {

    @Autowired
    private MockMvc mvc;



/*
    SaveEducationProviderResource saveEducationProviderResource;
    String resource;

    //WireMockServer
    public EducationProviderControllerStepdefs() {
    }
    @Given("^created saveEducationProviderResource$")
    public void createdSaveEducationProviderResource(){
        /*saveEducationProviderResource = new SaveEducationProviderResource();
        saveEducationProviderResource.setName("UPC");
        saveEducationProviderResource.setNumberOfCareers(37);*/
        //resource = "{\"name\":UPC, \"numberOfCareers\":37}]";
    /*}

    @When("^post http method is called$")
    public void postHttpMethodIsCalled() throws Exception{

        mvc.perform(MockMvcRequestBuilders
                .post("/api/educationProviders")
                .content(resource)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.educationProviderId").exists());


        /*
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8080//api/educationProviders");
        StringEntity = new StringEntity({
                "name": "UPC",
                "numberOfCareers": 37
    })//
    }

    @Then("^an education provider resource should be returned$")
    public void anEducationProviderResourceShouldBeReturned() {
        //answer
    }

    @Test
    public void givenSaveEducationProviderResource_whenPostEducationProvider_thenReturnCreatedStatus()
        throws Exception{
        String resource = "{\"name\":UPC, \"numberOfCareers\":37}]";

        mvc.perform(MockMvcRequestBuilders
                .post("/api/educationProviders")
                .content(resource)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.educationProviderId").exists());

    }
}
*/