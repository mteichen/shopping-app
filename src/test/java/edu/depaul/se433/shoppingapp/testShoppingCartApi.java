package edu.depaul.se433.shoppingapp;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testShoppingCartApi {

  private transient ShoppingCartApi api;
  private transient ShoppingCart mockCart;
  private transient PurchaseAgent mockAgent;

  @BeforeEach
  void setup(){
    mockCart = mock(ShoppingCart.class);
    mockAgent = mock(PurchaseAgent.class);
    api = new ShoppingCartApi(mockCart, mockAgent);
  }

  @Test
  @DisplayName("verify that addItem calls appropriate functions ")
  void testAddItem(){
    doNothing().when(mockCart).addItem(any());

    PurchaseItem mockPurchase = mock(PurchaseItem.class);
    api.addItem(mockPurchase);
    verify(mockCart, times(1)).addItem(any());
  }

  @Test
  @DisplayName("test that getPrice calls correct methods")
  void testGetPrice(){
    when(mockCart.cost()).thenReturn(0.00);

    api.getPrice();
    verify(mockCart, times(1)).cost();
  }

  @Test
  @DisplayName("test that getAveragePurchase calls correct methods")
  void testGetAverage(){
    when(mockAgent.averagePurchase(anyString())).thenReturn(0.00);

    api.getAvergaPurchase("test");
    verify(mockAgent, times(1)).averagePurchase(any());
  }

}
