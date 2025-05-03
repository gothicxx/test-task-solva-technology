package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.entity.Currency;

import java.time.LocalDate;

public interface CurrencyJpaRepository extends JpaRepository<Currency, Long> {

    @NativeQuery("select exists (select 1 from currencies where date =: date)")
    boolean existsByDate(@Param("date") LocalDate exchangeDate);
}
