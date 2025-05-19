package solva.technology.solution.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.entity.SpendingLimit;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;
import solva.technology.solution.persistence.mapper.SpendingLimitMapper;
import solva.technology.solution.persistence.repository.jpa.SpendingLimitJpaRepository;
import solva.technology.solution.service.port.out.SpendingLimitPersistence;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpendingLimitJpaAdapter implements SpendingLimitPersistence {

    private final SpendingLimitJpaRepository spendingLimitJpaRepository;

    private final SpendingLimitMapper spendingLimitMapper;

    @Override
    public boolean existsByCategoryAndDate(ExpenseCategory expenseCategory, LocalDate date) {
        return spendingLimitJpaRepository.existsByCategoryAndDate(expenseCategory, date);
    }

    // todo that's horrible method for adapter, think about how to do better
    @Override
    public BigDecimal getLimitOrDefault(ExpenseCategory category) {
        BigDecimal limit = spendingLimitJpaRepository.findLatestByCategory(category)
                .map(SpendingLimit::getAmount)
                .orElse(BigDecimal.valueOf(1000));
        return limit;
    }

    @Override
    public void save(SpendingLimitCreateDto limit) {
        log.info("map to entity and save: {}", limit);
        spendingLimitJpaRepository.save(spendingLimitMapper.mapToEntity(limit));
    }
}
