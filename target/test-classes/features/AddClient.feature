Feature: Checking "Add Client" functionality

  @positive
  Scenario: User is able to create a client
    Given base url
    When user provides valid authorization token
    And user selects a tag
    And user provides company name
    And user provides client's full name
    And user provides email
    And user provides phone number
    And user provides address
    And user hits POST endpoint "/api/myaccount/clients"
    Then user verifies status code is 201

