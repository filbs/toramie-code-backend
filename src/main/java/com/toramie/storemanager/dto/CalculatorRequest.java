package com.toramie.storemanager.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CalculatorRequest {
    @NotNull (message = "Price cannot be empty")
    private BigDecimal priceInYuan;
    private String itemCategory;  //DOLLS or OTHERS
    private String itemType; // 10CM, 20CM, 40CM, or weightBased
    private BigDecimal weightInput;

    public BigDecimal getPriceInYuan() {
        return priceInYuan;
    }

    public void setPriceInYuan(BigDecimal priceInYuan) {
        this.priceInYuan = priceInYuan;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getWeightInput() {
        return weightInput;
    }

    public void setWeightInput(BigDecimal weightInput) {
        this.weightInput = weightInput;
    }
}
