package solva.technology.solution.service.port.out;

import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyPersistence {

    boolean existsByDate(LocalDate date);

    void saveAll(List<CurrencyCreateDto> currencyDtoList);
}
