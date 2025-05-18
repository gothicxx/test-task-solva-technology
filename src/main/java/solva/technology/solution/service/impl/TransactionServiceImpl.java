package solva.technology.solution.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.dto.transaction.TransactionLimitExceededDto;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;
import solva.technology.solution.service.exception.ExchangeRateNotFoundException;
import solva.technology.solution.service.port.in.CurrencyService;
import solva.technology.solution.service.port.in.SpendingLimitService;
import solva.technology.solution.service.port.in.TransactionService;
import solva.technology.solution.service.port.out.TransactionPersistence;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionPersistence transactionPersistence;

    private final CurrencyService currencyService;

    private final SpendingLimitService spendingLimitService;

    @Override
    public void processAndSave(TransactionDto dto) {

        BigDecimal newTxUsd = convertToUsd(dto);

        BigDecimal currentMonthSpentUsd = sumAllSpentInUsdForMonth(
                dto.getExpenseCategory(),
                dto.getDateTime()
        );

        BigDecimal limitUsd = spendingLimitService.getLimitOrDefault(dto.getExpenseCategory());

        dto.setLimitExceeded(currentMonthSpentUsd.add(newTxUsd).compareTo(limitUsd) > 0);

        transactionPersistence.save(dto);
    }

    @Override
    public List<TransactionLimitExceededDto> getTransactionsWithExceededLimit() {
        return transactionPersistence.getTransactionsWithExceededLimit();
    }

    private BigDecimal convertToUsd(TransactionDto tx) {
        if (tx.getCurrencyShortName().equalsIgnoreCase("USD")) {
            return BigDecimal.valueOf(tx.getSum());
        }

        LocalDate txDate = tx.getDateTime().toLocalDate();
        CurrencyDto currency = currencyService.findOneByDateAndCurrencyCode(txDate, tx.getCurrencyShortName());

        if (currency.getRate() == null || BigDecimal.valueOf(currency.getRate()).compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExchangeRateNotFoundException("Exchange rate " + tx.getCurrencyShortName() + " for date "
                    + txDate + " not found and equal to zero");
        }

        return BigDecimal.valueOf(tx.getSum())
                .divide(BigDecimal.valueOf(currency.getRate()), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal sumAllSpentInUsdForMonth(ExpenseCategory category, OffsetDateTime dateTime) {
        LocalDate start = dateTime.toLocalDate().withDayOfMonth(1);
        LocalDate end = dateTime.toLocalDate().withDayOfMonth(dateTime.toLocalDate().lengthOfMonth());

        List<TransactionDto> txs = transactionPersistence.findAllByCategoryAndDateRange(category,
                start.atStartOfDay(),
                end.atTime(23,59,59));

        return txs.stream()
                .map(this::convertToUsd)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
