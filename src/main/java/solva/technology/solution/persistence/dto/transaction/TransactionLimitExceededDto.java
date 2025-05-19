package solva.technology.solution.persistence.dto.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record TransactionLimitExceededDto(

        Long transactionId,

        Long accountFrom,

        Long accountTo,

        String currencyShortName,

        Double sum,

        OffsetDateTime dateTime,

        ExpenseCategory expenseCategory,

        BigDecimal limitAmount,

        LocalDate limitDate,

        String limitCurrency
) {}
