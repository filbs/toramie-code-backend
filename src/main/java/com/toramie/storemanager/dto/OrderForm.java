package com.toramie.storemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderForm {
    @NotBlank
    private String customerName;
    @NotBlank
    private String customerAddress;
    private String phoneNumber;
    @NotBlank
    private String itemType;
    @NotBlank
    private String itemName;
    @NotNull
    private Integer quantity;
    @NotNull
    private BigDecimal priceInYuan;
    private BigDecimal yuanRate;

    private BigDecimal weightInput;

    private BigDecimal extras;
    private BigDecimal domesticPostage;
    private BigDecimal downPayment;
    private BigDecimal repayment;
    @NotBlank
    private String paymentStatus; // 'FP', 'DP'
    @NotBlank
    private String platform; // 'WD', 'XHS'
    private String shippingStatus;
    private String information;
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

    public BigDecimal getWeightInput() {
        return weightInput;
    }

    public void setWeightInput(BigDecimal weightInput) {
        this.weightInput = weightInput;
    }

    public BigDecimal getYuanRate() {
        return yuanRate;
    }

    public void setYuanRate(BigDecimal yuanRate) {
        this.yuanRate = yuanRate;
    }

    public BigDecimal getExtras() {
        return extras;
    }

    public void setExtras(BigDecimal extras) {
        this.extras = extras;
    }

    public BigDecimal getDomesticPostage() {
        return domesticPostage;
    }

    public void setDomesticPostage(BigDecimal domesticPostage) {
        this.domesticPostage = domesticPostage;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}

