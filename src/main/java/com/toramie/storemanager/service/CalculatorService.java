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

    public CalculatorSettings updateSettings(CalculatorSettings incoming) {
        CalculatorSettings existing = repository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Settings not found in database! Use SQL to seed ID 1 first."));

        // 2. Overwrite the existing data with form data
        // We add safety checks (?:) to prevent NULLs from crashing the DB
        existing.setYuanRate(incoming.getYuanRate() != null ? incoming.getYuanRate() : existing.getYuanRate());
        existing.setRateDollProfit(incoming.getRateDollProfit() != null ? incoming.getRateDollProfit() : existing.getRateDollProfit());
        existing.setRateOthersProfit(incoming.getRateOthersProfit() != null ? incoming.getRateOthersProfit() : existing.getRateOthersProfit());

        existing.setDoll10CMProfit(incoming.getDoll10CMProfit() != null ? incoming.getDoll10CMProfit() : existing.getDoll10CMProfit());
        existing.setDoll20CMProfit(incoming.getDoll20CMProfit() != null ? incoming.getDoll20CMProfit() : existing.getDoll20CMProfit());
        existing.setDoll40CMProfit(incoming.getDoll40CMProfit() != null ? incoming.getDoll40CMProfit() : existing.getDoll40CMProfit());

        existing.setShippingFee10CM(incoming.getShippingFee10CM() != null ? incoming.getShippingFee10CM() : existing.getShippingFee10CM());
        existing.setShippingFee20CM(incoming.getShippingFee20CM() != null ? incoming.getShippingFee20CM() : existing.getShippingFee20CM());
        existing.setShippingFee40CM(incoming.getShippingFee40CM() != null ? incoming.getShippingFee40CM() : existing.getShippingFee40CM());
        existing.setShippingFeeWeightBased(incoming.getShippingFeeWeightBased() != null ? incoming.getShippingFeeWeightBased() : existing.getShippingFeeWeightBased());

        existing.setPackingFee(incoming.getPackingFee() != null ? incoming.getPackingFee() : existing.getPackingFee());
        existing.setRoundingValue(incoming.getRoundingValue() != null ? incoming.getRoundingValue() : existing.getRoundingValue());

        // 3. Save the modified object
        return repository.save(existing);
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
