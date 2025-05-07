package solva.technology.solution.service.port.in;

import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {

    boolean existsByDate(LocalDate date);

    List<CurrencyDto> findAllByDate(LocalDate date);

    List<CurrencyDto> findAll();

    CurrencyDto findOneByDateAndCurrencyCode(LocalDate date, String currencyCode);
}
