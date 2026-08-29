package com.dio.budgetai.tools;

import com.dio.budgetai.model.BudgetLimit;
import com.dio.budgetai.model.Transaction;
import com.dio.budgetai.repository.BudgetLimitRepository;
import com.dio.budgetai.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
public class TransactionTools {

    public record BudgetCheckRequest(String category, Double amount) {}
    public record BudgetCheckResponse(boolean allowed, String message) {}

    public record TransactionRequest(String description, Double amount, String category) {}
    public record TransactionResponse(boolean success, String details) {}

    @Bean
    public Function<BudgetCheckRequest, BudgetCheckResponse> checkBudgetLimit(BudgetLimitRepository limitRepo) {
        return request -> {
            var limitOpt = limitRepo.findByCategoryIgnoreCase(request.category());
            if (limitOpt.isPresent()) {
                BudgetLimit limit = limitOpt.get();
                if (limit.getCurrentSpent() + request.amount() > limit.getMonthlyLimit()) {
                    return new BudgetCheckResponse(false, "Limite orçamentário excedido para a categoria " + request.category());
                }
            }
            return new BudgetCheckResponse(true, "Limite disponível.");
        };
    }

    @Bean
    public Function<TransactionRequest, TransactionResponse> registerTransaction(
            TransactionRepository txRepo, BudgetLimitRepository limitRepo) {
        return request -> {
            txRepo.save(new Transaction(request.description(), request.amount(), request.category()));
            limitRepo.findByCategoryIgnoreCase(request.category()).ifPresent(limit -> {
                limit.setCurrentSpent(limit.getCurrentSpent() + request.amount());
                limitRepo.save(limit);
            });
            return new TransactionResponse(true, "Transação registrada com sucesso.");
        };
    }
}
