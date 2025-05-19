package solva.technology.solution.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import solva.technology.solution.persistence.dto.limit.SpendingLimitCreateDto;
import solva.technology.solution.persistence.dto.limit.SpendingLimitDto;
import solva.technology.solution.persistence.entity.SpendingLimit;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class SpendingLimitMapper {

    public abstract SpendingLimit mapToEntity(SpendingLimitCreateDto dto);

    public abstract SpendingLimitDto mapToDto(SpendingLimit spendingLimit);
}
