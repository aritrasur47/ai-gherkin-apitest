Feature: Validating Place API's

  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add place payload with name "<name>" and address "<address>"
    When User calls "AddPlaceAPI" with Post https request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      | name              | address                     |

  Scenario: Verify if place is being successfully deleted using DeletePlaceAPI
    Given Add place payload
    When User calls "AddPlaceAPI" with Post https request
    And User calls "DeletePlaceAPI" with Post https request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
