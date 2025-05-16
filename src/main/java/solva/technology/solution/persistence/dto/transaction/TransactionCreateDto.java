package solva.technology.solution.persistence.dto.transaction;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.OffsetDateTime;

@Setter
@Getter
public class TransactionCreateDto {

    private Long accountFrom;

    private Long accountTo;

    private String currencyShortName;

    private Double sum;

    private ExpenseCategory expenseCategory;

    private OffsetDateTime dateTime;
}
