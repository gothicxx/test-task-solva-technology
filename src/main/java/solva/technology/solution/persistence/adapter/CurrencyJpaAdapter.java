package solva.technology.solution.persistence.adapter;

import lombok.RequiredArgsConstructor;
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
public class CurrencyJpaAdapter implements CurrencyPersistence {

    private final CurrencyMapper currencyMapper;

    private final CurrencyJpaRepository currencyJpaRepository;

    private final DataFetcher dataFetcher;

    @Override
    public boolean existsByDate(LocalDate date) {
        return currencyJpaRepository.existsByDate(date);
    }

    // todo think how to do, save, however return by url or select form db, but it will additional query
    @Override
    public List<CurrencyDto> findAllByDate(LocalDate date) {
        List<CurrencyCreateDto> currencyCreateDtoList = dataFetcher.fetchDataFromJsonByDate(date);
        currencyJpaRepository.saveAll(currencyCreateDtoList.stream()
                .map(currencyMapper::mapToEntity)
                .toList());
//        return currencies.stream()
//                .map(currencyMapper::mapToDto)
//                .toList();
        return currencyJpaRepository.findAllByDate(date).stream()
                .map(currencyMapper::mapToDto)
                .toList();
    }

    // todo firstly save and than return, in scheduler saved yet
    @Override
    public List<CurrencyDto> findAll() {
        return currencyJpaRepository.findAll().stream().map(currencyMapper::mapToDto).toList();
    }

    @Override
    public CurrencyDto findOneByDateAndCurrencyCode(LocalDate date, String currencyCode) {
        List<CurrencyCreateDto> currencyCreateDtoList = dataFetcher.fetchDataFromJsonByDate(date);
        Currency currency = currencyCreateDtoList.stream()
                .filter(currencyCode::equals)
                .map(currencyMapper::mapToEntity)
                .findFirst()
                .orElse(null);
        // todo refactor code, bad practice
        if (currency == null) {
            throw new RuntimeException();
        }
        currencyJpaRepository.save(currency);
        return currencyMapper.mapToDto(currencyJpaRepository.findOneByDateAndCurrencyCode(date, currencyCode));
    }
}
