@edit_assignment
Feature: Edit Assignment for decision tree
  User is able to edit assignment for existing decision tree

  @edit_assignment1
  Scenario: Add, delete assignment for inactive decision tree
    Given open application
    And login as test user
    When select first inactive decision tree
    When add assignment SG_PERD_DENY
    Then verify if SG_PERD_DENY is assigned
    When remove assignment SG_PERD_DENY
