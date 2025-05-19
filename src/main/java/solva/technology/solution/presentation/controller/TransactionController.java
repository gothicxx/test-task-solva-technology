package solva.technology.solution.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.dto.transaction.TransactionLimitExceededDto;
import solva.technology.solution.service.port.in.TransactionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    // todo refactor
    @PostMapping(value = "/save")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        transactionService.processAndSave(transactionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/exceeded")
    public ResponseEntity<List<TransactionLimitExceededDto>> getTransactionsWithExceededLimit() {
        return ResponseEntity.ok(transactionService.getTransactionsWithExceededLimit());
    }
}
