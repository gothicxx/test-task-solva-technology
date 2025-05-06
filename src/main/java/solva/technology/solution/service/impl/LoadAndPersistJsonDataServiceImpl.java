package solva.technology.solution.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.entity.Currency;
import solva.technology.solution.persistence.external_api.DataFetcher;
import solva.technology.solution.persistence.mapper.CurrencyMapper;
import solva.technology.solution.persistence.repository.jpa.CurrencyJpaRepository;
import solva.technology.solution.service.port.out.CurrencyPersistence;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoadAndPersistJsonDataServiceImpl {

    private final DataFetcher dataFetcher;

    private final CurrencyPersistence currencyPersistence;

    // todo just for test, remove
    private final CurrencyJpaRepository repository;

    // todo just for test, remove2
    private final CurrencyMapper mapper;

    @Scheduled(initialDelay = 1000, fixedDelay = 36000000)
    public void loadData() {
        LocalDate today = LocalDate.now();
        if (currencyPersistence.existsByDate(today)) {
            log.info("Currencies for today already exist in DB. Skipping fetch.");
            return;
        }

        List<CurrencyCreateDto> currencyDtoList = dataFetcher.fetchDataFromJson();
        currencyDtoList.forEach(currency -> currency.setExchangeDate(today));

        List<Currency> currencies = currencyDtoList.stream().map(mapper::mapToEntity).toList();

        log.info("Saving today's currencies to DB");
        currencies.forEach(c -> log.info("Saving: {}", c));
        repository.saveAll(currencies);
    }
}
