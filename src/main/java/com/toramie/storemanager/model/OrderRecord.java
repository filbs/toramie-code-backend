package com.toramie.storemanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class OrderRecord {

    @Id
    private String orderId;

    @OneToOne(mappedBy = "orderRecord", cascade = CascadeType.ALL) //delete details if the order is deleted
    private CustomerDetails customerDetails;

    @OneToOne(mappedBy = "orderRecord", cascade = CascadeType.ALL)
    private OrderDetails orderDetails;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
