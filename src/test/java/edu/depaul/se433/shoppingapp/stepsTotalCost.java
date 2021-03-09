package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.mockito.Mockito.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.But;

public class stepsTotalCost {

  private transient double initialCost;
  private transient ShippingType shipping = STANDARD;
  private transient String state = "IL";
  private transient double expTotal;

  private ShoppingCart mockCart;

  @Given("The customer wants {} shipping")
  public void shipping_type(ShippingType ship){
    shipping = ship;
  }

  @Given("The customer wants to buy items worth {double}")
  public void items_worth(Double cost){
    initialCost = cost;
  }

  @And("The customer is shipping to {word}")
  public void state_shipping_to(String stateShip){
    state = stateShip;
  }

  @Then("The customer receives a total of {double}")
  public void total_received(double totalCost){
    expTotal = totalCost;
    double actTotal = TotalCostCalculator.calculate(initialCost, state, shipping);
   assertEquals(expTotal, actTotal);
  }

  @But("With a tax of {double} and shipping costs of {double}")
  public void total_bill_received(double expTax, double expShippingCosts){
    mockCart = mock(ShoppingCart.class);
    when(mockCart.cost()).thenReturn(initialCost);

    Bill bill = TotalCostCalculator.calculate(mockCart, state, shipping);
    double actTax = bill.getTax();
    assertEquals(expTax, actTax);
  }

}
