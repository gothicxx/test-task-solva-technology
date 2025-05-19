package solva.technology.solution.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;
import solva.technology.solution.service.port.in.SpendingLimitService;
import solva.technology.solution.service.port.out.SpendingLimitPersistence;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpendingLimitServiceImpl implements SpendingLimitService {

    private final SpendingLimitPersistence spendingLimitPersistence;

    @Override
    public void setLimit(SpendingLimitCreateDto dto) {
        LocalDate today = LocalDate.now();

        boolean alreadyExists = spendingLimitPersistence.existsByCategoryAndDate(dto.getExpenseCategory(), today);
        if (alreadyExists) {
            log.info("Limit already set for today");
            throw new IllegalStateException("Limit for " + dto.getExpenseCategory() + " already set for today.");
        }

        dto.setDate(LocalDate.now());

        spendingLimitPersistence.save(dto);
    }

    @Override
    public BigDecimal getLimitOrDefault(ExpenseCategory category) {
        return spendingLimitPersistence.getLimitOrDefault(category);
    }
}
