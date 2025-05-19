package solva.technology.solution.persistence.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.OffsetDateTime;

@Setter
@Getter
public class TransactionDto {

    private Long accountFrom;

    private Long accountTo;

    private String currencyShortName;

    private Double sum;

    private ExpenseCategory expenseCategory;

    private OffsetDateTime dateTime;

    private Boolean limitExceeded;
}
