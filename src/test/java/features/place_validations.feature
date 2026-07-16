Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully deleted using DeletePlaceAPI
    Given Add place payload with name "<name>" and address "<address>"
    When User calls "AddPlaceAPI" with Post https request
    And User calls "DeletePlaceAPI" with Post https request
    Then The API call got success with status code 200
    And "status" in response body is "OK"

    Examples:
      | name              | address                     |
      | Frontline house   | 29, side layout, cohen 09   |
      | Cafe Coffee Day   | 12, MG Road, Bangalore      |
      | Book Store        | 45, Park Street, Kolkata    |
