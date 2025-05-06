package solva.technology.solution.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.entity.Currency;
import solva.technology.solution.service.port.in.CurrencyService;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/rates")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping("/welcome")
    public String getWelcome() {
        return "welcome...";
    }

    @GetMapping("/all-today")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        List<CurrencyDto> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/all-by-date/{date}")
    public ResponseEntity<List<CurrencyDto>> getAllCurrenciesByDate(@PathVariable("date") LocalDate date) {
        List<CurrencyDto> allByDate = service.findAllByDate(date);
        return ResponseEntity.ok(allByDate);
    }

    @GetMapping("/one-by-date-cc/{date}/{currencyCode}")
    public ResponseEntity<CurrencyDto> getAllCurrencies(@PathVariable("date") LocalDate date,
                                                        @PathVariable("currencyCode") String currencyCode) {
        CurrencyDto oneByDateAndCurrencyCode = service.findOneByDateAndCurrencyCode(date, currencyCode);
        return ResponseEntity.ok(oneByDateAndCurrencyCode);
    }
}
