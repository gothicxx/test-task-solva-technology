package solva.technology.solution.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import solva.technology.solution.persistence.entity.base_entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "currencies",
        indexes = {
        @Index(name = "idx_currencies_reference", columnList = "reference")
    },
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"currencyCode", "exchangeDate"})
    }
)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Getter
public class Currency extends BaseEntity {

    @EqualsAndHashCode.Include
    private Integer reference;

    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private Double rate;

    @EqualsAndHashCode.Include
    @Column(name = "currency_code")
    private String currencyCode;

    @EqualsAndHashCode.Include
    @Column(name = "exchange_date")
    private LocalDate exchangeDate;
}
