package com.toramie.storemanager.service;

import java.math.BigDecimal;

//record to store shippingFee and profit value filter
public record TypeFees(BigDecimal shippingFee, BigDecimal profit) {
}
