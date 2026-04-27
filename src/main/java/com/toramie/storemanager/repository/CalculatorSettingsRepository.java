package com.toramie.storemanager.repository;

import com.toramie.storemanager.model.CalculatorSettings;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface CalculatorSettingsRepository extends JpaRepository<CalculatorSettings, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE calculator_settings SET " +
            "yuan_rate = :yuanRate, " +
            "rate_doll_profit = :dollProfit, " +
            "rate_others_profit = :othersProfit, " +
            "doll10cmprofit = :p10, " +
            "doll20cmprofit = :p20, " +
            "doll40cmprofit = :p40, " +
            "shipping_fee10cm = :s10, " +
            "shipping_fee20cm = :s20, " +
            "shipping_fee40cm = :s40, " +
            "shipping_feeweight_based = :sWeight, " +
            "packing_fee = :packing, " +
            "rounding_value = :rounding " +
            "WHERE id = 1", nativeQuery = true)
    int updateSettingsDirectly(
            @Param("yuanRate") BigDecimal yuanRate,
            @Param("dollProfit") BigDecimal dollProfit,
            @Param("othersProfit") BigDecimal othersProfit,
            @Param("p10") BigDecimal p10,
            @Param("p20") BigDecimal p20,
            @Param("p40") BigDecimal p40,
            @Param("s10") BigDecimal s10,
            @Param("s20") BigDecimal s20,
            @Param("s40") BigDecimal s40,
            @Param("sWeight") BigDecimal sWeight,
            @Param("packing") BigDecimal packing,
            @Param("rounding") BigDecimal rounding
    );
}
