package edu.depaul.se433.shoppingapp;

import static edu.depaul.se433.shoppingapp.ShippingType.NEXT_DAY;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/*
 Testing of TotalCostCalculator.calculate(double initialCost, String state, ShippingType shipping)
                                     returns double
----------------------------------------------------------------------------------------------------
    Test suite executes the following tests:
      -Equivalence Partition testing
        -Strong/Normal
        -Weak/Robust
      -Boundary testing
        -normal

                             ------Equivalence Classes------

              Variable                 Class             Representative Value
              ------------------------------------------------------------------
                                isFreeStandardShip
              Valid                    >50(free standard)       75.00
                                       0.01-50(standard costs)  25.00
              Invalid                  <=0                     -20.00
              ------------------------------------------------------------------
                                stateTax
              Valid                   6% tax                    IL
                                      no state tax              WI
              Invalid                 not a state               XX
                                      no state                  null
              ------------------------------------------------------------------
                                shipping type
              Valid                   standard                  STANDARD
                                      next day                  NEXT_DAY
              Invalid                 no type                   null
              ------------------------------------------------------------------
*/
/*                             ---Normal Boundary Test Cases---

  Desc                                initialCost         state         shipping        expected
  High-Cost Expensive(No max value)   1000.00              WI             STANDARD        1000.00
  High-Cost Nominal                   75.00                WI             STANDARD        75.00
  High-Cost Min+1                     50.02                WI             STANDARD        50.02
  High-Cost Min                       50.01                WI             STANDARD        50.01
   Low-Cost Max                       50.00                WI             STANDARD        60.00
   Low-Cost Max-1                     49.99                WI             STANDARD        59.99
   Low-Cost Nominal                   25.00                WI             STANDARD        35.00
   Low-Cost Min+1                     0.02                 WI             STANDARD        10.02
   Low-Cost Min                       0.01                 WI             STANDARD        10.01

----------------------------------------------------------------------------------------------------
*/

public class TotalCostCalculatorTest {

  /*Equivalence Class Testing*/

  private static Stream<Arguments> nextStrongNormal() {
    return Stream.of(
        Arguments.of(75.00, "IL", STANDARD, 79.50),
        Arguments.of(75.00, "IL", NEXT_DAY, 104.50),
        Arguments.of(75.00, "WI", STANDARD, 75.00),
        Arguments.of(75.00, "WI", NEXT_DAY, 100.00),
        Arguments.of(25.00, "IL", STANDARD, 36.50),
        Arguments.of(25.00, "IL", NEXT_DAY, 51.50),
        Arguments.of(25.00, "WI", STANDARD, 35.00),
        Arguments.of(25.00, "WI", NEXT_DAY, 50.00)
    );
  }

  private static Stream<Arguments> nextWeakRobust() {
    return Stream.of(
        Arguments.of(-20.00, "WI", STANDARD),
        Arguments.of(75.00, "XX", STANDARD),
        Arguments.of(25.00, null, NEXT_DAY),
        Arguments.of(25.00, "IL", null)
    );
  }

  private static Stream<Arguments> nextBoundary() {
    return Stream.of(
        Arguments.of(1000.00, "WI", STANDARD, 1000.00),
        Arguments.of(75.00, "WI", STANDARD, 75.00),
        Arguments.of(50.02, "WI", STANDARD, 50.02),
        Arguments.of(50.01, "WI", STANDARD, 50.01),
        Arguments.of(50.00, "WI", STANDARD, 60.00),
        Arguments.of(49.99, "WI", STANDARD, 59.99),
        Arguments.of(25.00, "WI", STANDARD, 35.00),
        Arguments.of(0.02, "WI", STANDARD, 10.02),
        Arguments.of(0.01, "WI", STANDARD, 10.01)
    );
  }

  @ParameterizedTest
  @DisplayName("Strong Normal Equivalence Tests")
  @MethodSource("nextStrongNormal")
  void strongNormalTest(double initialCost, String state, ShippingType shipping, double expected) {
    double actual = TotalCostCalculator.calculate(initialCost, state, shipping);
    assertEquals(expected, actual);
  }

  /*Boundary Testing*/

  @ParameterizedTest
  @DisplayName("Weak Robust Equivalence Tests")
  @MethodSource("nextWeakRobust")
  void weakRobustTest(double initialCost, String state, ShippingType shipping) {
    assertThrows(RuntimeException.class,
        () -> TotalCostCalculator.calculate(initialCost, state, shipping));
  }

  @ParameterizedTest
  @DisplayName("Normal Boundary Tests")
  @MethodSource("nextBoundary")
  void boundaryTest(double initialCost, String state, ShippingType shipping, double expected) {
    double actual = TotalCostCalculator.calculate(initialCost, state, shipping);
    assertEquals(expected, actual);
  }

}
