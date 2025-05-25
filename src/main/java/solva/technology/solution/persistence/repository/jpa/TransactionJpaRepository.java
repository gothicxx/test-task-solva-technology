package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solva.technology.solution.persistence.entity.Transaction;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select * " +
            "from transactions " +
            "where expense_category = :category " +
            "and datetime between :start and :end", nativeQuery = true)
    List<Transaction> findAllByCategoryAndDateRange(
            @Param("category") ExpenseCategory category,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query(value = "select * " +
            "from transactions " +
            "where limit_exceeded = true", nativeQuery = true)
    List<Transaction> findAllExceeded();
}
