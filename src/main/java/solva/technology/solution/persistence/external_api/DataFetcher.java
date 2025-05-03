package solva.technology.solution.persistence.external_api;

import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;

import java.time.LocalDate;
import java.util.List;

public interface DataFetcher {

    List<CurrencyCreateDto> fetchDataFromJson();

    List<CurrencyCreateDto> fetchDataFromJsonByDate(LocalDate date);
}
