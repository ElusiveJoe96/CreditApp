package ru.axiomatika.CreditApp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.axiomatika.CreditApp.dao.ContractDAO;
import ru.axiomatika.CreditApp.dto.ContractDTO;
import ru.axiomatika.CreditApp.dto.RequestDTO;
import ru.axiomatika.CreditApp.model.Contract;
import ru.axiomatika.CreditApp.service.ContractService;
import ru.axiomatika.CreditApp.util.mapper.ContractMapper;
import ru.axiomatika.CreditApp.util.mapper.RequestMapper;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractDAO contractDAO;
    private final ContractMapper mapper = ContractMapper.INSTANCE;
    private final RequestMapper requestMapper = RequestMapper.INSTANCE;

    @Override
    public ContractDTO getById(Long id) {
        Contract contract = contractDAO.findById(id);
        return mapper.contractToContractDto(contract);
    }

    @Override
    @Transactional
    public ContractDTO create(RequestDTO requestDTO) {

        Contract contract = new Contract();
        contract.setRequest(requestMapper.requestDTOToRequest(requestDTO));
        contract.setSigningDate(LocalDate.now());
        contract.setSigningStatus(false);

        contractDAO.save(contract);

        return mapper.contractToContractDto(contract);
    }

    @Override
    @Transactional
    public List<ContractDTO> findAll() {
        return contractDAO.findAll().stream()
                .map(mapper::contractToContractDto)
                .toList();
    }

    @Override
    @Transactional
    public ContractDTO signContract(Long id) {
        Contract contract = contractDAO.findById(id);
        contract.setSigningStatus(true);
        contract.setSigningDate(LocalDate.now());
        contractDAO.update(contract);
        return mapper.contractToContractDto(contract);
    }

}
