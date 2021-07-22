@ui
Feature:Ato Page

  Scenario Outline: ATO income tax calculation
    Given I am on <page-name> page
    When I enter income year as <year>
    And I enter taxable income as <income>
    And I enter residential type as <resi-type>
    And I submit the form
    Then I should see correct Income tax displayed as <tax>


    Examples:
      | year    | income | resi-type                  | page-name | tax       |
      | 2020-21 | 100000 | Resident for full year     | ato       | 22,967.00 |
      | 2019-20 | 120000 | Non-resident for full year | ato       | 40,350.00 |
      | 2018-19 | 110000 | Part-year resident         | ato       | 28,946.74 |
