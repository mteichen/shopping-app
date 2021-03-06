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
                      initial cost
Valid                   >0                      20.00
Invalid
                      state


                      shipping type


Results of testing will be documented in excel spreadsheet file: Teichen_shoppingApp_Spreadsheet
------------------------------------------------------------------------------------------------
*/

public class testDoubleCalculate {


}
