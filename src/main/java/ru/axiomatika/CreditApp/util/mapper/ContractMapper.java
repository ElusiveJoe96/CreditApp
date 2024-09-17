package ru.axiomatika.CreditApp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.axiomatika.CreditApp.dto.ContractDTO;
import ru.axiomatika.CreditApp.model.Contract;

@Mapper(uses = RequestMapper.class)
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "request", target = "requestDTO")
    ContractDTO contractToContractDto(Contract Contract);

    @Mapping(source = "requestDTO", target = "request")
    Contract contractDtoToContract(ContractDTO ContractDto);
}
