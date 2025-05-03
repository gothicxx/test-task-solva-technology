package solva.technology.solution.persistence.external_api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.dto.currency.CurrencyDto;
import solva.technology.solution.persistence.entity.Currency;
import solva.technology.solution.persistence.external_api.DataFetcher;
import solva.technology.solution.persistence.mapper.CurrencyMapper;

import java.util.Arrays;
import java.util.List;

import static solva.technology.solution.persistence.external_api.constant.ExternalApiConstant.JSON_URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataFetcherImpl implements DataFetcher {

    private final WebClient webClient;

    private final CurrencyMapper currencyMapper;

    public List<CurrencyCreateDto> fetchDataFromJson() {
        log.info("Sending HTTP request via WebClient: {}", JSON_URL);

        try {
            CurrencyCreateDto[] response = webClient
                    .get()
                    .uri(JSON_URL)
                    .retrieve()
                    .bodyToMono(CurrencyCreateDto[].class)
                    .block();

            if (response == null) {
                log.warn("Empty response received from {}", JSON_URL);
                return List.of();
            }

            log.info("Successfully fetched {} items", response.length);

            return Arrays.stream(response).toList();
        } catch (Exception e) {
            log.error("Exception occurred while calling external API", e);
            throw new RuntimeException("Failed to fetch or parse JSON from: " + JSON_URL, e);
        }
    }
}
