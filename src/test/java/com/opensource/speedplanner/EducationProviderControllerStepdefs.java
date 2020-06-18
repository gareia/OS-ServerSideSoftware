package com.opensource.speedplanner;

import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.resource.EducationProviderResource;
import com.opensource.speedplanner.resource.SaveEducationProviderResource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EducationProviderControllerStepdefs {

    SaveEducationProviderResource saveEducationProviderResource;
    //WireMockServer
    public EducationProviderControllerStepdefs() {
    }
    @Given("^create saveEducationProviderResource$")
    public void createSaveEducationProviderResource(){
        saveEducationProviderResource = new SaveEducationProviderResource();
        saveEducationProviderResource.setName("UPC");
        saveEducationProviderResource.setNumberOfCareers(37);
    }

    @When("^post http method is called$")
    public void postHttpMethodIsCalled() {
        //educationProviderResource =
    }

    @Then("^an education provider resource should be returned$")
    public void anEducationProviderResourceShouldBeReturned() {
    }
}
