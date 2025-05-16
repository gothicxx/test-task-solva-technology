package solva.technology.solution.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import solva.technology.solution.persistence.entity.base_entity.BaseEntity;
import solva.technology.solution.persistence.entity.enums.ExpenseCategory;

import java.time.OffsetDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
public class Transaction extends BaseEntity {

    @Column(name = "account_from")
    private Long accountFrom;

    @Column(name = "account_to")
    private Long accountTo;

    @Column(name = "currency_shortname")
    private String currencyShortName;

    private Double sum;

    @Column(name = "expense_category")
    @Enumerated(value = EnumType.STRING)
    private ExpenseCategory expenseCategory;

    private OffsetDateTime dateTime;

    @Column(name = "limit_exceeded")
    private Boolean limitExceeded;
}
