package solva.technology.solution.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import solva.technology.solution.persistence.entity.Transaction;

public interface TransactionJpaRepository extends JpaRepository<Transaction, Long> {

}
