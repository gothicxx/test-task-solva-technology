package solva.technology.solution.service.port.in;

import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;

public interface SpendingLimitService {

    void setLimit(SpendingLimitCreateDto dto);

    BigDecimal getLimitOrDefault(ExpenseCategory category);
}
