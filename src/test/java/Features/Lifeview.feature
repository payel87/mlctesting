@ui
  Feature:Lifeview Page

  Scenario Outline: MLC Lifeview Page validation
    Given I am on <page-name> page
    When I search for <search-word> on Homepage
    And I click on Lifeview link
    Then I should see the correct breadcrumbs successfully
    When  I click on Request a demo button
    Then I should be able to enter relevant data <name>,<company>,<email>,<phone>,<date>,<time>,<details> in the form


    Examples:
      | name      | company | email        | phone        | date       | time | details       | search-word | page-name    |
      | test user | test    | test@abc.com | +61466000000 | 12/12/2017 | am   | Lifeview test | Lifeview    | mlclifeview |
