package solva.technology.solution.service.port.out;

import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.dto.transaction.TransactionLimitExceededDto;
import solva.technology.solution.persistence.entity.Transaction;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionPersistence {

    void save(TransactionDto dto);

    List<TransactionDto> findAllByCategoryAndDateRange(ExpenseCategory category,
                                                       LocalDateTime start,
                                                       LocalDateTime end);

    List<TransactionLimitExceededDto> getTransactionsWithExceededLimit();
}
