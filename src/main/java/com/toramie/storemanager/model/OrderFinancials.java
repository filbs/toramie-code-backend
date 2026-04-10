package com.toramie.storemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderFinancials {

    @Id
    private String orderId;

    private BigDecimal priceInYuan;
    private BigDecimal yuanRate;
    private BigDecimal domPostage;
    private BigDecimal idrAmount; //total rupiah per item: (yuan price + postage) * Exchange rate
    private BigDecimal shippingFee; //based on item type
    private Integer quantity;
    private BigDecimal extras;
    private BigDecimal modal; //(total rupiah per item + extras) * quantity
    private BigDecimal itemTypeProfit;
    private BigDecimal weightInput;
    private BigDecimal price; //total payment: ((rate + 400) * (yuan price + postage) + shipping + profit
    private BigDecimal finalPrice; //rounding up price
    private BigDecimal downPayment;
    private BigDecimal repayment;
    private BigDecimal dpProfit; // dp - modal
    private BigDecimal profit; //repayment - (quantity * (shipping + extras))
    private BigDecimal totalProfit; // dp profit + profit
    private BigDecimal tProfitWithShipping; // total profit + (shipping * quantity)
    private String information;

    private String paymentStatus; // DP or FP
    private String shippingStatus; // arrived or shipping
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

    public BigDecimal getPriceInYuan() {
        return priceInYuan;
    }

    public void setPriceInYuan(BigDecimal priceInYuan) {
        this.priceInYuan = priceInYuan;
    }

    public BigDecimal getYuanRate() {
        return yuanRate;
    }

    public void setYuanRate(BigDecimal yuanRate) {
        this.yuanRate = yuanRate;
    }

    public BigDecimal getDomPostage() {
        return domPostage;
    }

    public void setDomPostage(BigDecimal domPostage) {
        this.domPostage = domPostage;
    }

    public BigDecimal getIdrAmount() {
        return idrAmount;
    }

    public void setIdrAmount(BigDecimal idrAmount) {
        this.idrAmount = idrAmount;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getExtras() {
        return extras;
    }

    public void setExtras(BigDecimal extras) {
        this.extras = extras;
    }

    public BigDecimal getModal() {
        return modal;
    }

    public void setModal(BigDecimal modal) {
        this.modal = modal;
    }

    public BigDecimal getItemTypeProfit() {
        return itemTypeProfit;
    }

    public void setItemTypeProfit(BigDecimal itemTypeProfit) {
        this.itemTypeProfit = itemTypeProfit;
    }

    public BigDecimal getWeightInput() {
        return weightInput;
    }

    public void setWeightInput(BigDecimal weightInput) {
        this.weightInput = weightInput;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
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

    public BigDecimal getDpProfit() {
        return dpProfit;
    }

    public void setDpProfit(BigDecimal dpProfit) {
        this.dpProfit = dpProfit;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTProfitWithShipping() {
        return tProfitWithShipping;
    }

    public void settProfitWithShipping(BigDecimal tProfitWithShipping) {
        this.tProfitWithShipping = tProfitWithShipping;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public OrderRecord getOrderRecord() {
        return orderRecord;
    }

    public void setOrderRecord(OrderRecord orderRecord) {
        this.orderRecord = orderRecord;
    }
}
