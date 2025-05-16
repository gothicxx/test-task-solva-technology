package solva.technology.solution.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import solva.technology.solution.persistence.dto.transaction.TransactionCreateDto;
import solva.technology.solution.persistence.dto.transaction.TransactionDto;
import solva.technology.solution.persistence.entity.Transaction;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TransactionMapper {

    public abstract Transaction mapToEntity(TransactionCreateDto dto);

    public abstract TransactionDto mapToDto(Transaction transaction);
}
