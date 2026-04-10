package com.toramie.storemanager.repository;

import com.toramie.storemanager.model.OrderRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRecordRepository extends JpaRepository<OrderRecord, String> {

    @Query(value = "SELECT o.* FROM order_record o " +
            "JOIN customer_details c ON o.order_id = c.order_id " +
            "JOIN order_details d ON o.order_id = d.order_id " +
            "JOIN order_financials f ON o.order_id = f.order_id " +
            "WHERE o.order_id ILIKE %:query% " +
            "OR c.customer_name ILIKE %:query% " +
            "OR d.item_name ILIKE %:query% " +
            "OR f.payment_status ILIKE %:query% " +
            "OR f.shipping_status ILIKE %:query%",
            nativeQuery = true)
    List<OrderRecord> searchOrders(@Param("query") String query);

    @Query(value = "SELECT order_id FROM order_record " +
            "ORDER BY CAST(SUBSTRING(order_id FROM '[0-9]+') AS INTEGER) DESC " +
            "LIMIT 1", nativeQuery = true)
    String findLastOrderIdNumeric();
}
