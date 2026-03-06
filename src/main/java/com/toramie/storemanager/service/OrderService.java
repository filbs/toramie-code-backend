package com.toramie.storemanager.service;

import com.toramie.storemanager.dto.OrderForm;
import com.toramie.storemanager.model.*;
import com.toramie.storemanager.repository.CalculatorSettingsRepository;
import com.toramie.storemanager.repository.OrderRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Locale;

@Service
public class OrderService {
    private final OrderRecordRepository orderRepo;
    private final CalculatorSettingsRepository settingsRepo;

    public OrderService(OrderRecordRepository orderRepo, CalculatorSettingsRepository settingsRepo) {
        this.orderRepo = orderRepo;
        this.settingsRepo = settingsRepo;
    }

    @Transactional
    public void saveOrder(OrderForm form) {
        CalculatorSettings settings = settingsRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        String orderId = generateCustomId(form.getPlatform());
        OrderRecord master = new OrderRecord(orderId);

        CustomerDetails customer = mapCustomer(form, master);
        OrderDetails details = mapOrderDetails(form, master);
        OrderFinancials financials = mapFinancials(form, master, settings);

        applyCalculation(financials, settings, details.getItemType());

        master.setCustomerDetails(customer);
        master.setOrderDetails(details);
        master.setOrderFinancials(financials);

        orderRepo.save(master);
    }

    @Transactional
    public void updateOrder(String id, OrderForm form) {
        CalculatorSettings settings = settingsRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        OrderRecord master = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        CustomerDetails customer = mapCustomer(form, master);
        OrderDetails details = mapOrderDetails(form, master);
        OrderFinancials financials = mapFinancials(form, master, settings);

        applyCalculation(financials, settings, details.getItemType());

        master.setCustomerDetails(customer);
        master.setOrderDetails(details);
        master.setOrderFinancials(financials);

        orderRepo.save(master);
    }

    private String generateCustomId(String platform) {
        return platform + String.format("%03d", orderRepo.count() + 1);
    }

    private CustomerDetails mapCustomer(OrderForm form, OrderRecord master) {
        CustomerDetails c = new CustomerDetails();
        c.setCustomerName(form.getCustomerName());
        c.setCustomerAddress(form.getCustomerAddress());
        c.setPhoneNumber(form.getPhoneNumber());

        //child to parent (master)
        c.setOrderRecord(master);
        return c;
    }

    private OrderDetails mapOrderDetails(OrderForm form, OrderRecord master) {
        OrderDetails d = new OrderDetails();
        d.setItemType(form.getItemType());
        d.setItemName(form.getItemName());
        d.setQuantity(form.getQuantity());
        d.setPriceInYuan(form.getPriceInYuan());
        d.setOrderStatus(form.getPaymentStatus());

        d.setOrderRecord(master);
        return d;
    }

    private OrderFinancials mapFinancials(OrderForm form, OrderRecord master, CalculatorSettings settings) {
        OrderFinancials f = new OrderFinancials();

        f.setOrderRecord(master);
        f.setPriceInYuan(form.getPriceInYuan());
        f.setDomPostage(form.getDomesticPostage() != null ? form.getDomesticPostage() : BigDecimal.ZERO);
        f.setQuantity(form.getQuantity());
        f.setExtras(form.getExtras() != null ? form.getExtras() : BigDecimal.ZERO);
        f.setDownPayment(form.getDownPayment() != null ? form.getDownPayment() : BigDecimal.ZERO);
        f.setRepayment(form.getRepayment() != null ? form.getRepayment() : BigDecimal.ZERO);

        BigDecimal rate = (form.getYuanRate() != null ? form.getYuanRate() : settings.getYuanRate());
        f.setYuanRate(rate);

        f.setPaymentStatus(form.getPaymentStatus());
        f.setShippingStatus(form.getShippingStatus());
        f.setInformation(form.getInformation());

        return f;
    }

    public void applyCalculation(OrderFinancials f, CalculatorSettings s, String itemType) {
        BigDecimal shipFeeConst = getShippingFeeByType(s, itemType);
        BigDecimal sizeProfitConst = getSizeProfitByType(s,itemType);
        BigDecimal qty = new BigDecimal(f.getQuantity());

        //total IDR calculation
        BigDecimal idrValue = f.getPriceInYuan().add(f.getDomPostage());
        f.setIdrAmount(idrValue.multiply(f.getYuanRate()));

        f.setShippingFee(shipFeeConst);
        f.setItemTypeProfit(sizeProfitConst.add(new BigDecimal("5000")));

        //total item(s) modal price
        f.setModal(f.getExtras().add(f.getIdrAmount()).multiply(qty));


        //total price for each item with shipping and profit
        BigDecimal price = f.getYuanRate().add(new BigDecimal("400"));
        price = price.multiply(f.getPriceInYuan());
        f.setPrice(price.add(shipFeeConst).add(f.getItemTypeProfit()));

        //total price for item(s) while rounded to the nearest 1000
        BigDecimal thousand = new BigDecimal("1000");
        BigDecimal rounded = f.getPrice()
                .divide(thousand, 0, RoundingMode.CEILING).multiply(thousand);
        f.setFinalPrice(rounded.multiply(qty));

        //total profit from down payment
        f.setDpProfit(f.getDownPayment().subtract(f.getModal()));

        //total profit from repayment
        BigDecimal profit = f.getRepayment()
                .subtract(f.getShippingFee().add(f.getExtras())).multiply(qty);
        f.setProfit(profit);

        //final total profit
        f.setTotalProfit(f.getDpProfit().add(f.getProfit()));

        //final total profit with shipping
        f.settProfitWithShipping(f.getTotalProfit().add(f.getShippingFee().multiply(qty)));
    }

    private BigDecimal getShippingFeeByType(CalculatorSettings s, String type) {
        if (type == null) return BigDecimal.ZERO;

        return switch (type.toUpperCase()) {
            case "10CM" -> s.getShippingFee10CM();
            case "20CM" -> s.getShippingFee20CM();
            case "40CM" -> s.getShippingFee40CM();
            case "WEIGHT_BASED" -> s.getShippingFeeWeightBased();
            default -> BigDecimal.ZERO;
        };
    }

    private BigDecimal getSizeProfitByType(CalculatorSettings s, String type) {
        if (type == null) return BigDecimal.ZERO;

        return switch (type.toUpperCase()) {
            case "10CM" -> s.getDoll10CMProfit();
            case "20CM" -> s.getDoll20CMProfit();
            case "40CM" -> s.getDoll40CMProfit();
            default -> BigDecimal.ZERO;
        };
    }

    public List<OrderRecord> getAllOrders() {
        return orderRepo.findAll();
    }
}
