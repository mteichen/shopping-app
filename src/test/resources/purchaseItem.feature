Feature: Testing functionality of quantity usage in purchasing.

  Background:
    Given Customer wants to a computer product
    And Product costs 1000.00

  Scenario: Customer makes a 3 quantity item purchase.
    Given Customer wants 3
    Then Value of purchases is 3000.00

  Scenario: Customer makes an invalid 0 quantity item purchase.
    Given Customer wants 0
    Then Customer gets an error

