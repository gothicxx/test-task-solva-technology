package solva.technology.solution.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.external_api.DataFetcher;
import solva.technology.solution.service.port.in.CurrencyService;
import solva.technology.solution.service.port.out.CurrencyPersistence;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyPersistence currencyPersistence;

    private final DataFetcher dataFetcher;

    @Override
    public boolean existsByDate(LocalDate date) {
        return currencyPersistence.existsByDate(date);
    }

    // firstly fetch data by url from external api
    // after save in DB
    // and get data from DB by date.
    // todo mb better try to save data in DB,
    //  but return fetched data by url and don't do additional select from DB
    @Override
    public List<CurrencyDto> findAllByDate(LocalDate date) {
        LocalDate effectiveDate = getEffectiveDate(date);
        return currencyPersistence.findAllByDate(effectiveDate);
    }

    public CurrencyDto findOneByDateAndCurrencyCode(LocalDate date, String currencyCode) {
        LocalDate effectiveDate = getEffectiveDate(date);
        return currencyPersistence.findOneByDateAndCurrencyCode(effectiveDate, currencyCode);
    }

    @Override
    public List<CurrencyDto> findAll() {
        return currencyPersistence.findAll();
    }

    // previous_close
    private LocalDate getEffectiveDate (LocalDate date) {
        return switch (date.getDayOfWeek()) {
            case SATURDAY -> date.minusDays(1);
            case SUNDAY -> date.minusDays(2);
            default -> date;
        };
    }
}
