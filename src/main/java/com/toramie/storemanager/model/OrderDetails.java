package com.toramie.storemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderDetails {

    @Id
    private String orderId;

    private String itemType;
    private String itemName;
    private Integer quantity;
    private BigDecimal priceInYuan;
    private String orderStatus;

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private OrderRecord orderRecord;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getPriceInYuan() {
        return priceInYuan;
    }

    public void setPriceInYuan(BigDecimal priceInYuan) {
        this.priceInYuan = priceInYuan;
    }

    public OrderRecord getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(OrderRecord orderRecord) {
        this.orderRecord = orderRecord;
    }
}
