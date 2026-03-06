package com.toramie.storemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class CustomerDetails {

    @Id
    private String orderId;

    private String customerName;
    private String customerAddress;
    private String phoneNumber;

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

    public OrderRecord getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(OrderRecord orderRecord) {
        this.orderRecord = orderRecord;
    }
}
