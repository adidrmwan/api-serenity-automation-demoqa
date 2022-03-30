Feature: Delete User Endpoint

  @delete-existing-user
  Scenario: Delete Existing User
    Given admin success auth using new user
    When admin set endpoint for delete existing user
    And admin send delete endpoint
    Then admin received HTTP response 204