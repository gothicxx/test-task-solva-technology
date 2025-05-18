package solva.technology.solution.persistence.dto.limit;

import lombok.Getter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class SpendingLimitDto {

    private ExpenseCategory expenseCategory;

    private BigDecimal amount;

    private LocalDate date;
}
