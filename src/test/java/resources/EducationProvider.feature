Feature: EducationProvider controller
  Scenario: Create an education provider
    Given created saveEducationProviderResource
    When post http method is called
    Then an education provider resource should be returned