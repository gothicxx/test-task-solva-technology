package solva.technology.solution.persistence.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
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
        List<Currency> currencies = currencyCreateDtoList.stream().map(currencyMapper::mapToEntity).toList();
        currencyJpaRepository.saveAll(currencies);
        //        currencyJpaRepository.saveAll(currencyCreateDtoList.stream()
//                .map(currencyMapper::mapToEntity)
//                .toList());
        return currencies.stream()
                .map(currencyMapper::mapToDto)
                .toList();
//        return currencyJpaRepository.findAllByDate(date).stream()
//                .map(currencyMapper::mapToDto)
//                .toList();
    }

    // todo firstly save and than return, in scheduler saved yet
    @Override
    public List<CurrencyDto> findAll() {
        List<CurrencyDto> list = currencyJpaRepository.findAll().stream()
                .map(currencyMapper::mapToDto)
                .toList();
        log.info("Returning {} currencies", list.size());
        return list;

      //  return currencyJpaRepository.findAll().stream().map(currencyMapper::mapToDto).toList();
    }

    @Override
    public CurrencyDto findOneByDateAndCurrencyCode(LocalDate date, String currencyCode) {
        Optional<Currency> oneByDateAndCurrencyCode = currencyJpaRepository
                .findOneByDateAndCurrencyCode(date, currencyCode);

        if (oneByDateAndCurrencyCode.isPresent()) {
            return currencyMapper.mapToDto(oneByDateAndCurrencyCode.get());
        }

        List<CurrencyCreateDto> currencyCreateDtoList = dataFetcher.fetchDataFromJsonByDate(date);

        CurrencyCreateDto matchingCurrencyCreateDto = currencyCreateDtoList.stream()
                .filter(dto -> dto.getCurrencyCode().equalsIgnoreCase(currencyCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                "Currency with code " + currencyCode + " not found for date " + date));


        Currency saved = currencyJpaRepository.save(currencyMapper.mapToEntity(matchingCurrencyCreateDto));
        return currencyMapper.mapToDto(saved);
    }

    public void saveAll(List<CurrencyCreateDto> dto) {
        List<Currency> currencies = dto.stream().map(currencyMapper::mapToEntity).toList();
        currencyJpaRepository.saveAll(currencies);
    }
}
