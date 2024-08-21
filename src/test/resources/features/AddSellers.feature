Feature: Checking "Add Sellers" functionality

  @positive1
  Scenario: User is able to create a seller
    Given base url
    When user provides valid authorization token
    And user provides a title
    And user provides client's full name
    And user provides email
    And user provides phone number
    And user provides address
    And user hits POST endpoint "/api/myaccount/clients"
    Then user verifies status code is 201