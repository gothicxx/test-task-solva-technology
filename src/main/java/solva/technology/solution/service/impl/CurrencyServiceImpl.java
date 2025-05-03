package solva.technology.solution.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.service.port.in.CurrencyService;
import solva.technology.solution.service.port.out.CurrencyPersistence;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyPersistence currencyPersistence;

    @Override
    public boolean existsByDate(LocalDate date) {
        return currencyPersistence.existsByDate(date);
    }

    @Override
    public void saveAll(List<CurrencyCreateDto> currencyDtoList) {
        currencyPersistence.saveAll(currencyDtoList);
    }
}
