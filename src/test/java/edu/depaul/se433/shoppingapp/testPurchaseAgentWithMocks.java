package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.jdbi.v3.core.JdbiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testPurchaseAgentWithMocks {

  private PurchaseDBO mockDBO;
  private PurchaseAgent purchaseAgent;

  @BeforeEach
  void setupMock(){
    mockDBO = mock(PurchaseDBO.class);
    purchaseAgent = new PurchaseAgent(mockDBO);
  }

  @Test
  @DisplayName("averagePurchase() test for filled list")
  void testAveragePurchaseFilledList(){
    //give mock intended behavior for getting purchases
    List<Purchase> mockList = new ArrayList<>();
    mockList.add(Purchase.make("Test", LocalDate.now(), 60.00, "WI", "STANDARD"));
    mockList.add(Purchase.make("Test", LocalDate.now(), 70.00, "WI", "STANDARD"));
    mockList.add(Purchase.make("Test", LocalDate.now(), 80.00, "WI", "STANDARD"));
    when(mockDBO.getPurchases(anyString())).thenReturn(mockList);

    double actual = purchaseAgent.averagePurchase("Test");
    double expected = 70.00;
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("averagePurchase() test for empty list")
  void testAveragePurchaseEmptyList(){
    //give mock intended behavior for getting purchases
    List<Purchase> mockList = new ArrayList<>();
    when(mockDBO.getPurchases(anyString())).thenReturn(mockList);

    double actual = purchaseAgent.averagePurchase("Test");
    double expected = 0;
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Test save() calls PurchaseDBO.savePurchase() function")
  void testSave(){
    //give mock intended behavior for saving purchase
    Purchase test = Purchase.make("Test", LocalDate.now(), 60.00, "WI", "STANDARD");
    doNothing().when(mockDBO).savePurchase(any());

    purchaseAgent.save(test);
    verify(mockDBO, times(1)).savePurchase(any());
  }

  @Test
  @DisplayName("Test getPurchases() calls PurchaseDBO.getPurchases() function")
  void testGetPurchases(){
    //give mock intended behavior for getting purchases
    when(mockDBO.getPurchases(anyString())).thenReturn(new ArrayList<>());

    purchaseAgent.getPurchases("Test");
    verify(mockDBO, times(1)).getPurchases(anyString());
  }

  @Test
  @DisplayName("Test getPurchases() returns empty list if name not found")
  void testGetPurchasesNotFound(){
    List<Purchase> actual = purchaseAgent.getPurchases("Test");
    List<Purchase> expected = new ArrayList<>();
    assertEquals(expected, actual);
  }
}
