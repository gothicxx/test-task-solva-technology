package solva.technology.solution.service.port.in;

import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyService {

    boolean existsByDate(LocalDate date);

    void saveAll(List<CurrencyCreateDto> currencyDtoList);
}
