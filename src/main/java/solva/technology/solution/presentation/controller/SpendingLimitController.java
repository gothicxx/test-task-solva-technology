package solva.technology.solution.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.dto.limit.SpendingLimitDto;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;
import solva.technology.solution.service.port.in.SpendingLimitService;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/limits")
@RequiredArgsConstructor
@Slf4j
public class SpendingLimitController {

    private final SpendingLimitService spendingLimitService;

    @PostMapping(value = "/set")
    public ResponseEntity<SpendingLimitDto> setLimit(@RequestBody SpendingLimitCreateDto dto) {
        spendingLimitService.setLimit(dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(value = "/{category}")
    public ResponseEntity<BigDecimal> getLimitByCategory(@PathVariable("category") ExpenseCategory category) {
        return ResponseEntity.ok(spendingLimitService.getLimitOrDefault(category));
    }
}
