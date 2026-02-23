package com.toramie.storemanager.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderForm {
    @NotNull
    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String itemType;
    private String itemName;
    private Integer quantity;
    private BigDecimal priceInYuan;
    private String orderStatus; // 'FP', 'DP'
    private String platform; // 'WD', 'XHS'

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public BigDecimal getPriceInYuan() {
        return priceInYuan;
    }

    public void setPriceInYuan(BigDecimal priceInYuan) {
        this.priceInYuan = priceInYuan;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}

