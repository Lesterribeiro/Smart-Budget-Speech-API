package com.dio.budgetai.repository;

import com.dio.budgetai.model.BudgetLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BudgetLimitRepository extends JpaRepository<BudgetLimit, Long> {
    Optional<BudgetLimit> findByCategoryIgnoreCase(String category);
}
