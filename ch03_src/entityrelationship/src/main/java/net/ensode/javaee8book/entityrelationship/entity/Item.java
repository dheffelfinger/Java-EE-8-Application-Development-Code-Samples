package net.ensode.javaee8book.entityrelationship.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Item
{
  @Id
  @Column(name = "ITEM_ID")
  private Long itemId;

  @Column(name = "ITEM_NUMBER")
  private String itemNumber;

  @Column(name = "ITEM_SHORT_DESC")
  private String itemShortDesc;

  @Column(name = "ITEM_LONG_DESC")
  private String itemLongDesc;

  @ManyToMany(mappedBy="items")
  private Collection<Order> orders;

  public Long getItemId()
  {
    return itemId;
  }

  public void setItemId(Long itemId)
  {
    this.itemId = itemId;
  }

  public String getItemLongDesc()
  {
    return itemLongDesc;
  }

  public void setItemLongDesc(String itemLongDesc)
  {
    this.itemLongDesc = itemLongDesc;
  }

  public String getItemNumber()
  {
    return itemNumber;
  }

  public void setItemNumber(String itemNumber)
  {
    this.itemNumber = itemNumber;
  }

  public String getItemShortDesc()
  {
    return itemShortDesc;
  }

  public void setItemShortDesc(String itemShortDesc)
  {
    this.itemShortDesc = itemShortDesc;
  }

  public Collection<Order> getOrders()
  {
    return orders;
  }

  public void setOrders(Collection<Order> orders)
  {
    this.orders = orders;
  }

}
