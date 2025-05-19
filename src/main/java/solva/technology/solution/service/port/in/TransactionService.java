package solva.technology.solution.service.port.in;

import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.dto.transaction.TransactionLimitExceededDto;

import java.util.List;

public interface TransactionService {

    void processAndSave(TransactionDto dto);

    List<TransactionLimitExceededDto> getTransactionsWithExceededLimit();
}
