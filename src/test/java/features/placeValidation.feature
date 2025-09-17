Feature: Validating Place APIs

  Scenario: Verify place is being successfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceAPI" with Post http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


@AddPlace
  Scenario Outline: Verify place is being successfully added using AddPlaceAPI with examples
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      | name   | language | address       |  |
      | Reddy  | Telugu   | Hyderabad     |  |
      | Kadapa | Hindi    | Andhrapradesh |  |

  Scenario Outline: Verify place is being successfully added with same details using AddPlaceAPI with examples
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name     | language | address     |  |
      | Sagar    | Telugu   | Kadapa      |  |
      | VS Reddy | English  | Rayalaseema |  |
