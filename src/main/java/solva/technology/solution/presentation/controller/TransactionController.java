package solva.technology.solution.presentation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solva.technology.solution.persistence.dto.transaction.TransactionCreateDto;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.entity.Transaction;

@RestController
@RequestMapping("api/v1/transactions")
@Slf4j
public class TransactionController {

    @PostMapping(value = "/save")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        return null;
    }
}
