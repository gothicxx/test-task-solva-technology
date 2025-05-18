package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.entity.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyJpaRepository extends JpaRepository<Currency, Long> {

    @NativeQuery(value = "select count(*) > 0 " +
            "from currencies " +
            "where exchange_date = :date")
    boolean existsByDate(@Param("date") LocalDate exchangeDate);

    @NativeQuery("select * " +
            "from currencies " +
            "where exchange_date = :date")
    List<Currency> findAllByDate(@Param("date") LocalDate date);

    @NativeQuery("select * " +
            "from currencies " +
            "where exchange_date = :date " +
            "and currency_code = :cc")
    Optional<Currency> findOneByDateAndCurrencyCode(@Param("date") LocalDate date,
                                                    @Param("cc") String currencyCode);
}
