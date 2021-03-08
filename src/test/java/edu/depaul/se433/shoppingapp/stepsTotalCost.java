package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.mockito.Mockito.*;

import edu.depaul.se433.shoppingapp.Bill;
import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.ShoppingCart;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.But;

public class stepsTotalCost {

  private double initialCost = 0;
  private ShippingType shipping = STANDARD;
  private String state = "IL";
  private double expTotal = 0;
  private double taxRate = 0.06;
  private double shipCosts = 0;

  private ShoppingCart mockCart;

  @Given("The customer wants {} shipping")
  void shipping_type(ShippingType ship){
    shipping = ship;
  }

  @Given("The customer wants to buy items worth {double}")
  void items_worth(Double cost){
    initialCost = cost;
  }

  @And("The customer is shipping to {word}")
  void state_shipping_to(String stateShip){
    state = stateShip;
  }

  @Then("The customer receives a total of {double}")
  void total_received(double totalCost){
    expTotal = totalCost;
    double actTotal = TotalCostCalculator.calculate(initialCost, state, shipping);
   assertEquals(expTotal, actTotal);
  }

  @But("With a tax of {double} and shipping costs of {double}")
  void total_bill_received(double expTax, double expShippingCosts){
    mockCart = mock(ShoppingCart.class);
    when(mockCart.cost()).thenReturn(initialCost);

    Bill bill = TotalCostCalculator.calculate(mockCart, state, shipping);
    double actTax = bill.getTax();
    assertEquals(expTax, actTax);
  }

}