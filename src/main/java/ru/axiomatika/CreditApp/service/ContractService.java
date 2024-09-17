package ru.axiomatika.CreditApp.service;

import ru.axiomatika.CreditApp.dto.ContractDTO;
import ru.axiomatika.CreditApp.dto.RequestDTO;

import java.util.List;

public interface ContractService {

    ContractDTO create(RequestDTO requestDTO);

    ContractDTO getById(Long id);

    List<ContractDTO> findAll();

    ContractDTO signContract(Long id);
}
