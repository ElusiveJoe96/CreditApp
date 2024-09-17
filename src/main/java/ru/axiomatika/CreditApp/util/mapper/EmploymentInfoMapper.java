package ru.axiomatika.CreditApp.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.axiomatika.CreditApp.dto.EmploymentInfoDTO;
import ru.axiomatika.CreditApp.model.EmploymentInfo;

@Mapper
public interface EmploymentInfoMapper {
    EmploymentInfoMapper INSTANCE = Mappers.getMapper(EmploymentInfoMapper.class);

    EmploymentInfoDTO emplInfoToEmplInfoDTO(EmploymentInfo employmentInfo);

    EmploymentInfo emplInfoDTOToEmplInfo(EmploymentInfoDTO employmentInfoDto);
}
