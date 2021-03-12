/*
 * SE 333 Class project
 * Author: Dan Walker
 * Copyright 2020
 */
package edu.depaul.se433.shoppingapp;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import java.time.LocalDate;

/**
 * Purchase is a value object used to represent the content of a purchase to the
 * outside world.
 */
public class Purchase {
  private int idNum;
  private String customerName;
  private LocalDate purchaseDate;
  private double cost;
  private String state;

  public String getShipping() {
    return shipping;
  }

  public void setShipping(String shipping) {
    this.shipping = shipping;
  }

  private String shipping;

  public static Purchase make(String name, LocalDate date, double cost, String state, String shipping) {
    Purchase p = new Purchase();
    p.setCustomerName(name);
    p.setPurchaseDate(date);
    p.setCost(cost);
    p.setState(state);
    p.setShipping(shipping);
    return p;
  }

  public int getIdNum() {
    return idNum;
  }

  public void setIdNum(int idNum) {
    this.idNum = idNum;
  }

  @ColumnName("name")
  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  @ColumnName("pdate")
  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Purchase(name: ")
            .append(this.customerName)
            .append(" date: ")
            .append(this.purchaseDate)
            .append(")");
    return builder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Purchase purchase = (Purchase) o;

    if (!this.customerName.equals(purchase.getCustomerName())) return false;
    if (!this.purchaseDate.equals(purchase.getPurchaseDate())) return false;
    if (Double.compare(this.cost, purchase.getCost()) != 0) return false;
    if (!this.state.equals(purchase.getState())) return false;
    return (this.shipping.equals(purchase.getShipping()));
  }

}