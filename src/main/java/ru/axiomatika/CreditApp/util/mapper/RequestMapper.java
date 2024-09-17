package ru.axiomatika.CreditApp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.axiomatika.CreditApp.dto.RequestDTO;
import ru.axiomatika.CreditApp.model.Request;

@Mapper(uses = {ClientMapper.class, ContractMapper.class})
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "client", target = "clientDTO")
    RequestDTO requestToRequestDTO(Request request);

    @Mapping(source = "clientDTO", target = "client")
    Request requestDTOToRequest(RequestDTO requestDTO);

}
