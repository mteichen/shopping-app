package edu.depaul.se433.shoppingapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PurchaseDBOTest {

  private transient PurchaseDBO dbo;

  @BeforeEach
  void setup() throws IOException {
    dbo = new PurchaseDBO();
  }

  @Test
  @DisplayName("test that purchaseDBO properly communicates with database and saves a purchase")
  void testSavePurchase() {
    Purchase actual = Purchase.make("test",
        LocalDate.of(1999, 1, 1),
        1.00,
        "WI",
        "NEXT_DAY");

    dbo.savePurchase(actual);

    //get purchase from database

    List<Purchase> purchaseList = dbo.getPurchases("test");
    Purchase expected = purchaseList.get(purchaseList.size() - 1);

    assertEquals(actual, expected);
  }

}
