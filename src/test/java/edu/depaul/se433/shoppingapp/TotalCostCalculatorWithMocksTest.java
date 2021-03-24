package edu.depaul.se433.shoppingapp;

import static edu.depaul.se433.shoppingapp.ShippingType.NEXT_DAY;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
Testing of TotalCostCalculator.calculate(ShoppingCart cart, String state, ShippingType shipping)
                                    returns Bill(initialCost,shippingCost,tax, total)
------------------------------------------------------------------------------------------------
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
              Valid                   6% tax (IL)               IL
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
  High-Cost Expensive(No max value)   1000.00              WI             STANDARD        Bill(1000.00, 0, 0, 1000.00)
  High-Cost Nominal                   75.00                WI             STANDARD        Bill(75.00, 0, 0, 75.00)
  High-Cost Min+1                     50.02                WI             STANDARD        Bill(50.02, 0, 0, 50.02)
  High-Cost Min                       50.01                WI             STANDARD        Bill(50.01, 0, 0, 50.01)
   Low-Cost Max                       50.00                WI             STANDARD        Bill(50.00, 10.00, 0, 60.00)
   Low-Cost Max-1                     49.99                WI             STANDARD        Bill(49.99, 10.00, 0, 59.99)
   Low-Cost Nominal                   25.00                WI             STANDARD        Bill(25.00, 10.00, 0, 35.00)
   Low-Cost Min+1                     0.02                 WI             STANDARD        Bill(0.02, 10.00, 0, 10.02)
   Low-Cost Min                       0.01                 WI             STANDARD        Bill(0.01, 10.00, 0, 10.01)

----------------------------------------------------------------------------------------------------
*/

public class TotalCostCalculatorWithMocksTest {

  private transient ShoppingCart mockCart;

  /*Equivalence Class Testing*/

  private static Stream<Arguments> nextStrongNormal() {
    return Stream.of(
        Arguments.of(75.00, "IL", STANDARD, new Bill(75.00, 0, 4.50, 79.50)),
        Arguments.of(75.00, "IL", NEXT_DAY, new Bill(75.00, 25.00, 4.50, 104.50)),
        Arguments.of(75.00, "WI", STANDARD, new Bill(75.00, 0, 0, 75.00)),
        Arguments.of(75.00, "WI", NEXT_DAY, new Bill(75.00, 25.00, 0, 100.00)),
        Arguments.of(25.00, "IL", STANDARD, new Bill(25.00, 10.00, 1.50, 36.50)),
        Arguments.of(25.00, "IL", NEXT_DAY, new Bill(25.00, 25.00, 1.50, 51.50)),
        Arguments.of(25.00, "WI", STANDARD, new Bill(25.00, 10.00, 0, 35.00)),
        Arguments.of(25.00, "WI", NEXT_DAY, new Bill(25.00, 25.00, 0, 50.00))
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
        Arguments.of(1000.00, "WI", STANDARD, new Bill(1000.00, 0, 0, 1000.00)),
        Arguments.of(75.00, "WI", STANDARD, new Bill(75.00, 0, 0, 75.00)),
        Arguments.of(50.02, "WI", STANDARD, new Bill(50.02, 0, 0, 50.02)),
        Arguments.of(50.01, "WI", STANDARD, new Bill(50.01, 0, 0, 50.01)),
        Arguments.of(50.00, "WI", STANDARD, new Bill(50.00, 10.00, 0, 60.00)),
        Arguments.of(49.99, "WI", STANDARD, new Bill(49.99, 10.00, 0, 59.99)),
        Arguments.of(25.00, "WI", STANDARD, new Bill(25.00, 10.00, 0, 35.00)),
        Arguments.of(0.02, "WI", STANDARD, new Bill(0.02, 10.00, 0, 10.02)),
        Arguments.of(0.01, "WI", STANDARD, new Bill(0.01, 10.00, 0, 10.01))
    );
  }

  @BeforeEach
  void setup() {
    mockCart = mock(ShoppingCart.class);
  }

  @ParameterizedTest
  @DisplayName("Strong Normal Equivalence Tests")
  @MethodSource("nextStrongNormal")
  void strongNormalTest(double initialCost, String state, ShippingType shipping, Bill expected) {
    when(mockCart.cost()).thenReturn(initialCost);
    Bill actual = TotalCostCalculator.calculate(mockCart, state, shipping);
    assertEquals(expected, actual);
  }

  /*Boundary Testing*/

  @ParameterizedTest
  @DisplayName("Weak Robust Equivalence Tests")
  @MethodSource("nextWeakRobust")
  void weakRobustTest(double initialCost, String state, ShippingType shipping) {
    when(mockCart.cost()).thenReturn(initialCost);
    assertThrows(RuntimeException.class,
        () -> TotalCostCalculator.calculate(mockCart, state, shipping));
  }

  @ParameterizedTest
  @DisplayName("Normal Boundary Tests")
  @MethodSource("nextBoundary")
  void boundaryTest(double initialCost, String state, ShippingType shipping, Bill expected) {
    when(mockCart.cost()).thenReturn(initialCost);
    Bill actual = TotalCostCalculator.calculate(mockCart, state, shipping);
    assertEquals(expected, actual);
  }

}
