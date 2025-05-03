package solva.technology.solution.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.entity.Currency;
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

    @Override
    public boolean existsByDate(LocalDate date) {
        return currencyJpaRepository.existsByDate(date);
    }

    @Override
    public void saveAll(List<CurrencyCreateDto> currencyDtoList) {
        List<Currency> currencies = currencyDtoList.stream().map(currencyMapper::mapToEntity).toList();
        currencyJpaRepository.saveAll(currencies);
    }
}
