package com.toramie.storemanager.service;

import com.toramie.storemanager.dto.OrderForm;
import com.toramie.storemanager.model.CustomerDetails;
import com.toramie.storemanager.model.OrderDetails;
import com.toramie.storemanager.model.OrderRecord;
import com.toramie.storemanager.repository.OrderRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRecordRepository orderRecordRepository;

    public OrderService(OrderRecordRepository orderRecordRepository) {
        this.orderRecordRepository = orderRecordRepository;
    }

    @Transactional
    public void saveOrder(OrderForm form) {
        long recordCount = orderRecordRepository.count();
        String newId = form.getPlatform() + String.format("%03d", recordCount + 1);

        OrderRecord master = new OrderRecord();
        master.setOrderId(newId);

        CustomerDetails customer = new CustomerDetails();
        customer.setCustomerName(form.getCustomerName());
        customer.setCustomerAddress(form.getCustomerAddress());
        customer.setPhoneNumber(form.getPhoneNumber());

        //child to parent (master)
        customer.setOrderRecord(master);

        OrderDetails details = new OrderDetails();
        details.setItemType(form.getItemType());
        details.setItemName(form.getItemName());
        details.setQuantity(form.getQuantity());
        details.setPriceInYuan(form.getPriceInYuan());
        details.setOrderStatus(form.getOrderStatus());

        details.setOrderRecord(master);

        master.setCustomerDetails(customer);
        master.setOrderDetails(details);

        orderRecordRepository.save(master);
        }
}
