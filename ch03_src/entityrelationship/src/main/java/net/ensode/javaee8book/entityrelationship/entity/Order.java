package net.ensode.javaee8book.entityrelationship.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order
{
  @Id
  @Column(name = "ORDER_ID")
  private Long orderId;

  @Column(name = "ORDER_NUMBER")
  private String orderNumber;

  @Column(name = "ORDER_DESCRIPTION")
  private String orderDescription;

  @ManyToOne
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;

  @ManyToMany
  @JoinTable(name = "ORDER_ITEMS", 
      joinColumns = @JoinColumn(name = "ORDER_ID", 
          referencedColumnName = "ORDER_ID"), 
          inverseJoinColumns = @JoinColumn(name = "ITEM_ID", 
              referencedColumnName = "ITEM_ID"))
  private Collection<Item> items;

  public Customer getCustomer()
  {
    return customer;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public String getOrderDescription()
  {
    return orderDescription;
  }

  public void setOrderDescription(String orderDescription)
  {
    this.orderDescription = orderDescription;
  }

  public Long getOrderId()
  {
    return orderId;
  }

  public void setOrderId(Long orderId)
  {
    this.orderId = orderId;
  }

  public String getOrderNumber()
  {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber)
  {
    this.orderNumber = orderNumber;
  }

  public Collection<Item> getItems()
  {
    return items;
  }

  public void setItems(Collection<Item> items)
  {
    this.items = items;
  }
}
