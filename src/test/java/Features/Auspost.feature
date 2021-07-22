@api
Feature: Validate session call

  Scenario Outline:Check Shipping cost for 3 countries
    Given I read the base url
    When I make a get call for shipping cost for <country_code> , <weight> and <service_code>
    Then The response status is "200"
    And I save the response in "Calculate Shipping Call Response_<country_code>"
    And The actual response values should match with "shipping_<country_code>_<weight>" reference json for "$.postage_result.costs.cost.item"
    And The actual response values should match with "shipping_<country_code>_<weight>" reference json for "$.postage_result.costs.cost.cost"

    Examples:
    |country_code|weight|service_code|
    |NZ          |1.0   |INT_PARCEL_STD_OWN_PACKAGING|
    |IN          |10.0  |INT_PARCEL_STD_OWN_PACKAGING|
    |AT          |12.0  |INT_PARCEL_STD_OWN_PACKAGING|

