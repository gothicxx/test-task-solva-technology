package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.entity.Currency;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyJpaRepository extends JpaRepository<Currency, Long> {

    @Query(value = "select count(*) > 0 " +
            "from currencies " +
            "where exchange_date = :date", nativeQuery = true)
    boolean existsByDate(@Param("date") LocalDate exchangeDate);

    @Query(value = "select * " +
            "from currencies " +
            "where exchange_date = :date", nativeQuery = true)
    List<Currency> findAllByDate(@Param("date") LocalDate date);

    @Query(value = "select * " +
            "from currencies " +
            "where exchange_date = :date " +
            "and currency_code = :cc", nativeQuery = true)
    Optional<Currency> findOneByDateAndCurrencyCode(@Param("date") LocalDate date,
                                                    @Param("cc") String currencyCode);
}
