package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testShoppingCart {

  private ShoppingCart cart;
  private PurchaseItem mockItem;

  @BeforeEach
  void setup() {
    cart = new ShoppingCart();
    //mock purchaseItem
    mockItem = mock(PurchaseItem.class);
  }

  @Test
  @DisplayName("Test that addItem() adds appropriate items")
  void testAddItem(){
    cart.addItem(mockItem);
    int expected = 1;
    int actual = cart.itemCount();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test that clear() empties cart")
  void testItemClear(){
    cart.addItem(mockItem);
    cart.clear();
    int expected = 0;
    int actual = cart.itemCount();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test that cost() will return appropriate total for cart")
  void testItemCost(){
    cart.addItem(mockItem);
    when(mockItem.value()).thenReturn(10.00);

    double expected = 10.00;
    double actual = cart.cost();
    assertEquals(expected, actual);
  }
}
