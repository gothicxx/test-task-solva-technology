package solva.technology.solution.persistence.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Setter
@Getter
public class TransactionLimitExceededDto {

    private Long transactionId;

    private Long accountFro;

    private Long accountTo;

    private String currencyShortName;

    private Double sum;

    private OffsetDateTime dateTime;

    private ExpenseCategory expenseCategory;

    private BigDecimal limitAmount;

    private LocalDate limitDate;

    private String limitCurrency;
}
