Feature: Retrieve Sellers

  @getAllSellers
  Scenario: Get all sellers and validate email fields
    Given the base URL
    And I provide a valid authorization token
    And I store parameters in a hashmap
    When I send a GET request to retrieve all sellers "/api/myaccount/sellers"
    Then the response status should be 200
    And I should receive a list of sellers and verify seller's email is not empty
