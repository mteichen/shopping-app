package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import edu.depaul.se433.shoppingapp.PurchaseItem;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class stepsItemPurchaseQuantity {

  private String name;
  private double unitPrice = 0;
  private Integer quantity = 0;

  @Given("Customer wants to a {word} product")
  public void product_name(String productName){ name = productName; }

  @And("Product costs {double}")
  public void unit_price(double price) { unitPrice = price; }

  @Given("Customer wants {int}")
  public void product_quantity(Integer count) { quantity = count; }


  @Then("Value of purchases is {double}")
  public void purchase_price(double expTotal) {

    PurchaseItem item = new PurchaseItem(name, unitPrice, quantity);
    double actTotal = item.value();

    assertEquals(expTotal, actTotal);
  }

  @Then("Customer gets an error")
  public void check_error(){
    PurchaseItem item = new PurchaseItem(name, unitPrice, quantity);
    assertThrows(RuntimeException.class, item::value);
  }


}
