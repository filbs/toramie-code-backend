package com.toramie.storemanager.service;

import com.toramie.storemanager.dto.CalculatorRequest;
import com.toramie.storemanager.model.CalculatorSettings;
import com.toramie.storemanager.repository.CalculatorSettingsRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {
    private final CalculatorSettingsRepository repository;


    public CalculatorService(CalculatorSettingsRepository repository) {
        this.repository = repository;
    }

    public BigDecimal calculateTotal (CalculatorRequest request) {
        BigDecimal price;
        CalculatorSettings settings = repository.findById(1L).orElseThrow(() -> new RuntimeException("Settings not found!"));

        //rateModifier value filter
        BigDecimal rateModifier = getModifierByCategory(request, settings);

        //fees filter method value assignment
        TypeFees fees = getFeesByType(request, settings);
        BigDecimal shipping = fees.shippingFee();
        BigDecimal profit = fees.profit();

        //calculation for price from yuan to rupiah
        price = request.getPriceInYuan().multiply(settings.getYuanRate().add(rateModifier));

        //total calculation
        BigDecimal total = price.add(profit).add(shipping).add(settings.getPackingFee());

        return roundingCalculator(total, settings.getRoundingValue());
    }

    public CalculatorSettings getSettings () {
        return repository.findById(1L).orElseGet(() -> {
            CalculatorSettings defaults = new CalculatorSettings();
            defaults.setId(1L);
            defaults.setYuanRate(new BigDecimal("2000")); // Default starting rate
            // Set other mandatory fields to zero or a default string
            return defaults;
        });
    }

    public CalculatorSettings updateSettings(CalculatorSettings newSettings) {
        newSettings.setId(1L);
        return repository.save(newSettings);
    }

    private BigDecimal getModifierByCategory (CalculatorRequest request, CalculatorSettings settings) {
        BigDecimal rateModifier;
        if (request.getItemCategory().equalsIgnoreCase("DOLLS")) {
            rateModifier = settings.getRateDollProfit();
        }
        else {
            rateModifier = settings.getRateOthersProfit();
        }
        return rateModifier;
    }

    //Shipping Fee and profit value filter
    private TypeFees getFeesByType (CalculatorRequest request, CalculatorSettings settings) {
        String type = request.getItemType();

        if (type.equalsIgnoreCase("10CM")) {
            return new TypeFees(settings.getShippingFee10CM(), settings.getDoll10CMProfit());
        }
        else if (request.getItemType().equalsIgnoreCase("20CM")) {
            return new TypeFees(settings.getShippingFee20CM(), settings.getDoll20CMProfit());
        }
        else if (request.getItemType().equalsIgnoreCase("40CM")) {
            return new TypeFees(settings.getShippingFee40CM(), settings.getDoll40CMProfit());
        }
        else {
            if (request.getWeightInput() == null) {
                throw new IllegalArgumentException("Weight must be provided for weight-based shipping!");
            }
            else {
                BigDecimal weightShipping = settings.getShippingFeeWeightBased().multiply(request.getWeightInput());
                return new TypeFees(weightShipping, BigDecimal.ZERO);
            }
        }

    }

    //to calculate the value to the desired rounded up value
    private BigDecimal roundingCalculator (BigDecimal total, BigDecimal multiple) {
        BigDecimal quotient = total.divide(multiple,0, RoundingMode.CEILING);
        return quotient.multiply(multiple);
    }

}
