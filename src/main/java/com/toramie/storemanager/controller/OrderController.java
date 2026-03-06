package com.toramie.storemanager.controller;

import com.toramie.storemanager.dto.OrderForm;
import com.toramie.storemanager.model.OrderRecord;
import com.toramie.storemanager.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderForm orderForm) {
        orderService.saveOrder(orderForm);
        return ResponseEntity.ok("Order saved successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable String id,
                                              @RequestBody @Valid OrderForm orderForm) {
        orderService.updateOrder(id, orderForm);
        return ResponseEntity.ok("Order " + id + " has been successfully updated.");
    }

    @GetMapping
    public ResponseEntity<List<OrderRecord>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
