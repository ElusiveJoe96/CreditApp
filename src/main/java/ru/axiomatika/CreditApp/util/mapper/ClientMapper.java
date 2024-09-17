package ru.axiomatika.CreditApp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.model.Client;

@Mapper(uses = EmploymentInfoMapper.class)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "employmentInfo", target = "employmentInfoDTO")
    ClientDTO clientToClientDto(Client client);

    @Mapping(source = "employmentInfoDTO", target = "employmentInfo")
    Client clientDtoToClient(ClientDTO clientDto);
}
