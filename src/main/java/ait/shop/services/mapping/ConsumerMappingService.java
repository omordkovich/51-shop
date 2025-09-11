package ait.shop.services.mapping;

import ait.shop.model.dto.ConsumerDTO;
import ait.shop.model.entity.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsumerMappingService {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Consumer mapDtoToEntity(ConsumerDTO dto);

    ConsumerDTO mapEntityToDto(Consumer entity);

}
