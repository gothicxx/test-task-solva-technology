package solva.technology.solution.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.dto.transaction.TransactionLimitExceededDto;
import solva.technology.solution.persistence.entity.SpendingLimit;
import solva.technology.solution.persistence.entity.Transaction;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;
import solva.technology.solution.persistence.mapper.TransactionMapper;
import solva.technology.solution.persistence.repository.jpa.SpendingLimitJpaRepository;
import solva.technology.solution.persistence.repository.jpa.TransactionJpaRepository;
import solva.technology.solution.service.port.out.TransactionPersistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionJpaAdapter implements TransactionPersistence {

    private final TransactionJpaRepository transactionJpaRepository;

    private final SpendingLimitJpaRepository spendingLimitJpaRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public void save(TransactionDto dto) {
        log.info("map to entity and save: {}", dto);
        transactionJpaRepository.save(transactionMapper.mapToEntity(dto));
    }

    @Override
    public List<TransactionDto> findAllByCategoryAndDateRange(ExpenseCategory category,
                                                              LocalDateTime start,
                                                              LocalDateTime end) {
        return transactionJpaRepository.findAllByCategoryAndDateRange(category, start, end).stream()
                .map(transactionMapper::mapToDto)
                .toList();
    }

    @Override
    public List<TransactionLimitExceededDto> getTransactionsWithExceededLimit() {
        List<Transaction> transactions = transactionJpaRepository.findAllExceeded();

        return transactions.stream().map(tx -> {
            // Получаем лимит для категории
            Optional<SpendingLimit> optionalLimit = spendingLimitJpaRepository
                    .findLatestByCategory(tx.getExpenseCategory());

            BigDecimal limitAmount = optionalLimit
                    .map(SpendingLimit::getAmount)
                    .orElse(BigDecimal.valueOf(1000));

            LocalDate limitDate = optionalLimit
                    .map(SpendingLimit::getDate)
                    .orElse(null);

            return new TransactionLimitExceededDto(
                    tx.getId(),
                    tx.getAccountFrom(),
                    tx.getAccountTo(),
                    tx.getCurrencyShortName(),
                    tx.getSum(),
                    tx.getDateTime(),
                    tx.getExpenseCategory(),
                    limitAmount,
                    limitDate,
                    "USD"
            );
        }).toList();
    }
}
