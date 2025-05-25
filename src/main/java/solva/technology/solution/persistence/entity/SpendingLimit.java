package solva.technology.solution.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import solva.technology.solution.persistence.entity.base_entity.BaseEntity;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "spending_limits")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
public class SpendingLimit extends BaseEntity {

    @Column(name = "expense_category")
    @Enumerated(value = EnumType.STRING)
    private ExpenseCategory expenseCategory;

    private BigDecimal amount;

    @Column(name = "limit_date")
    private LocalDate date;
}
