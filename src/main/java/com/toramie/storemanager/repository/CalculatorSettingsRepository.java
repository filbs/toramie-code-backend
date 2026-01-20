package com.toramie.storemanager.repository;

import com.toramie.storemanager.model.CalculatorSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorSettingsRepository extends JpaRepository<CalculatorSettings, Long> {
}
