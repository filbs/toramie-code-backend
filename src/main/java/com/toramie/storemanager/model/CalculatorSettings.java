package com.toramie.storemanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "mst_calc_settings")
public class CalculatorSettings {
    @Id
    private Long id; // Remove @GeneratedValue because we force ID 1

    @Column(nullable = false)
    private BigDecimal yuanRate;

    @Column(nullable = false)
    private BigDecimal rateDollProfit;

    @Column(nullable = false)
    private BigDecimal rateOthersProfit;

    @Column(nullable = false)
    private BigDecimal doll10CMProfit;

    @Column(nullable = false)
    private BigDecimal doll20CMProfit;

    @Column(nullable = false)
    private BigDecimal doll40CMProfit;

    @Column(nullable = false)
    private BigDecimal shippingFee10CM;

    @Column(nullable = false)
    private BigDecimal shippingFee20CM;

    @Column(nullable = false)
    private BigDecimal shippingFee40CM;

    @Column(nullable = false)
    private BigDecimal shippingFeeWeightBased;

    @Column(nullable = false)
    private BigDecimal packingFee;

    @Column(nullable = false)
    private BigDecimal roundingValue;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getRoundingValue() {
        return roundingValue;
    }

    public void setRoundingValue(BigDecimal roundingValue) {
        this.roundingValue = roundingValue;
    }

    public BigDecimal getYuanRate() {
        return yuanRate;
    }

    public void setYuanRate(BigDecimal yuanRate) {
        this.yuanRate = yuanRate;
    }

    public BigDecimal getRateDollProfit() {
        return rateDollProfit;
    }

    public void setRateDollProfit(BigDecimal rateDollProfit) {
        this.rateDollProfit = rateDollProfit;
    }

    public BigDecimal getRateOthersProfit() {
        return rateOthersProfit;
    }

    public void setRateOthersProfit(BigDecimal rateOthersProfit) {
        this.rateOthersProfit = rateOthersProfit;
    }

    public BigDecimal getDoll10CMProfit() {
        return doll10CMProfit;
    }

    public void setDoll10CMProfit(BigDecimal doll10CMProfit) {
        this.doll10CMProfit = doll10CMProfit;
    }

    public BigDecimal getDoll20CMProfit() {
        return doll20CMProfit;
    }

    public void setDoll20CMProfit(BigDecimal doll20CMProfit) {
        this.doll20CMProfit = doll20CMProfit;
    }

    public BigDecimal getDoll40CMProfit() {
        return doll40CMProfit;
    }

    public void setDoll40CMProfit(BigDecimal doll40CMProfit) {
        this.doll40CMProfit = doll40CMProfit;
    }

    public BigDecimal getShippingFee10CM() {
        return shippingFee10CM;
    }

    public void setShippingFee10CM(BigDecimal shippingFee10CM) {
        this.shippingFee10CM = shippingFee10CM;
    }

    public BigDecimal getShippingFee20CM() {
        return shippingFee20CM;
    }

    public void setShippingFee20CM(BigDecimal shippingFee20CM) {
        this.shippingFee20CM = shippingFee20CM;
    }

    public BigDecimal getShippingFee40CM() {
        return shippingFee40CM;
    }

    public void setShippingFee40CM(BigDecimal shippingFee40CM) {
        this.shippingFee40CM = shippingFee40CM;
    }

    public BigDecimal getShippingFeeWeightBased() {
        return shippingFeeWeightBased;
    }

    public void setShippingFeeWeightBased(BigDecimal shippingFeeWeightBased) {
        this.shippingFeeWeightBased = shippingFeeWeightBased;
    }

    public BigDecimal getPackingFee() {
        return packingFee;
    }

    public void setPackingFee(BigDecimal packingFee) {
        this.packingFee = packingFee;
    }
}
