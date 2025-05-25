package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.entity.SpendingLimit;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.LocalDate;
import java.util.Optional;

public interface SpendingLimitJpaRepository extends JpaRepository<SpendingLimit, Long> {

    @Query(value = "select exists " +
            "(select 1 from spending_limits " +
            "where expense_category = :category " +
            "and date = :date)", nativeQuery = true)
    boolean existsByCategoryAndDate(@Param("category") ExpenseCategory category,
                                    @Param("date") LocalDate date);

    @Query(value = "select * " +
            "from spending_limits " +
            "where expense_category = :category " +
            "order by date desc" +
            "limit 1", nativeQuery = true)
    Optional<SpendingLimit> findLatestByCategory(@Param("category") ExpenseCategory category);
}
