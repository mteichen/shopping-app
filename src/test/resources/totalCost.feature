Feature: Testing that California and New York have 6% sales tax in total cost calculator

  Background:
    Given The customer wants STANDARD shipping

  Scenario: Customer is shipping to California 75.00 order
    Given The customer wants to buy items worth 75.00
    And The customer is shipping to CA
    Then The customer receives a total of 79.50
    But With a tax of 4.50 and shipping costs of 0

  Scenario: Customer is shipping to California 1000.00 order
    Given The customer wants to buy items worth 1000.00
    And The customer is shipping to CA
    Then The customer receives a total of 1060.00
    But With a tax of 60.00 and shipping costs of 0

  Scenario: Customer is shipping to New York 75.00 order
    Given The customer wants to buy items worth 75.00
    And The customer is shipping to NY
    Then The customer receives a total of 79.50
    But With a tax of 4.50 and shipping costs of 0

  Scenario: Customer is shipping to New York 1000.00 order
    Given The customer wants to buy items worth 1000.00
    And The customer is shipping to NY
    Then The customer receives a total of 1060.00
    But With a tax of 60.00 and shipping costs of 0