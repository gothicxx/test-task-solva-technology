package solva.technology.solution.service.port.out;

import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyPersistence {

    boolean existsByDate(LocalDate date);

    void saveAll(List<CurrencyCreateDto> dto);

    List<CurrencyDto> findAllByDate(LocalDate date);

    List<CurrencyDto> findAll();

    CurrencyDto findOneByDateAndCurrencyCode(LocalDate date, String currencyCode);
}
