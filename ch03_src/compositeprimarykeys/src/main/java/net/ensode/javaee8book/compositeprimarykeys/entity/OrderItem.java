package net.ensode.javaee8book.compositeprimarykeys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_ITEMS")
@IdClass(value = OrderItemPK.class)
public class OrderItem
{
  @Id
  @Column(name = "ORDER_ID")
  private Long orderId;

  @Id
  @Column(name = "ITEM_ID")
  private Long itemId;

  @Column(name = "ITEM_QTY")
  private Long itemQty;

  public Long getItemId()
  {
    return itemId;
  }

  public void setItemId(Long itemId)
  {
    this.itemId = itemId;
  }

  public Long getItemQty()
  {
    return itemQty;
  }

  public void setItemQty(Long itemQty)
  {
    this.itemQty = itemQty;
  }

  public Long getOrderId()
  {
    return orderId;
  }

  public void setOrderId(Long orderId)
  {
    this.orderId = orderId;
  }
}
