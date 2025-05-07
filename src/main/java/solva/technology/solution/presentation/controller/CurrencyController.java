package solva.technology.solution.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.service.port.in.CurrencyService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/rates")
@RequiredArgsConstructor
@Slf4j
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping("/all-today")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        log.info("Received request to get currencies list");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/all-by-date")
    public ResponseEntity<List<CurrencyDto>> getAllCurrenciesByDate(@RequestParam("date") LocalDate date) {
        log.info("Received request to get currencies list by date: {}", date);
        return ResponseEntity.ok(service.findAllByDate(date));
    }

    @GetMapping("/one-by-date-currencyCode")
    public ResponseEntity<CurrencyDto> getOneByDateAndCurrencyCode(@RequestParam("date") LocalDate date,
                                                        @RequestParam("currencyCode") String currencyCode) {
        log.info("Received request to get currency by date: {} and currencyCode: {}", date, currencyCode);
        return ResponseEntity.ok(service.findOneByDateAndCurrencyCode(date, currencyCode));
    }
}
