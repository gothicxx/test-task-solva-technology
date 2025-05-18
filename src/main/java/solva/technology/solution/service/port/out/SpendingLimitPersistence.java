package solva.technology.solution.service.port.out;

import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.dto.limit.SpendingLimitDto;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SpendingLimitPersistence {

    boolean existsByCategoryAndDate(ExpenseCategory category, LocalDate date);

    BigDecimal getLimitOrDefault(ExpenseCategory category);

    void save(SpendingLimitCreateDto limit);
}
