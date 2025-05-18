package solva.technology.solution.persistence.dto.limit;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class SpendingLimitCreateDto {

    @NotNull
    private ExpenseCategory expenseCategory;

    @DecimalMin("0.01")
    private BigDecimal amount;

    private LocalDate date;
}
