package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


/*
Testing of TotalCostCalculator.calculate(double initial cost, String state, ShippingType shipping)
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
                                       0-50(standard costs)     25.00
              Invalid                  <0                      -20.00
              ------------------------------------------------------------------
                                stateTax
              Valid                   6% tax (IL, CA, NY)       IL
                                      no state tax              WI
              Invalid                 not a state               XX
              ------------------------------------------------------------------
                                shipping type
              Valid                   standard                  STANDARD
                                      next day                  NEXT_DAY
              Invalid                 no type                   null
              ------------------------------------------------------------------

Results of this testing suite in excel spreadsheet file: ./Teichen_shoppingApp_Spreadsheet
----------------------------------------------------------------------------------------------------
*/

public class testDoubleCalculate {


}
