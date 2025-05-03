package solva.technology.solution.persistence.external_api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import solva.technology.solution.persistence.dto.currency.CurrencyCreateDto;
import solva.technology.solution.persistence.external_api.DataFetcher;
import solva.technology.solution.persistence.mapper.CurrencyMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static solva.technology.solution.persistence.external_api.constant.ExternalApiConstant.CURRENT_DAY_JSON_URL;
import static solva.technology.solution.persistence.external_api.constant.ExternalApiConstant.NBU_OFF_SITE_URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataFetcherImpl implements DataFetcher {

    private final WebClient webClient;

    private final CurrencyMapper currencyMapper;

    public List<CurrencyCreateDto> fetchDataFromJsonByDate(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String urlWithDate = String.format(NBU_OFF_SITE_URL +
                "NBUStatService/v1/statdirectory/exchange?date=%s&json", formattedDate);

        log.info("Sending HTTP request via WebClient for date: {} -> URL: {}", date, urlWithDate);

        try {
            CurrencyCreateDto[] response = webClient
                    .get()
                    .uri(urlWithDate)
                    .retrieve()
                    .bodyToMono(CurrencyCreateDto[].class)
                    .block();

            if (response == null) {
                log.warn("Empty response received from {}", urlWithDate);
                return List.of();
            }

            log.info("Successfully fetched {} items for date {}", response.length, date);

            return Arrays.stream(response).toList();
        } catch (Exception e) {
            log.error("Exception occured while calling external API for date {}", date, e);
            throw new RuntimeException("Failed to fetch or parse JSON for date " + date, e);
        }
    }

    public List<CurrencyCreateDto> fetchDataFromJson() {
        log.info("Sending HTTP request via WebClient: {}", CURRENT_DAY_JSON_URL);

        try {
            CurrencyCreateDto[] response = webClient
                    .get()
                    .uri(CURRENT_DAY_JSON_URL)
                    .retrieve()
                    .bodyToMono(CurrencyCreateDto[].class)
                    .block();

            if (response == null) {
                log.warn("Empty response received from {}", CURRENT_DAY_JSON_URL);
                return List.of();
            }

            log.info("Successfully fetched {} items", response.length);

            return Arrays.stream(response).toList();
        } catch (Exception e) {
            log.error("Exception occurred while calling external API", e);
            throw new RuntimeException("Failed to fetch or parse JSON from: " + CURRENT_DAY_JSON_URL, e);
        }
    }
}
