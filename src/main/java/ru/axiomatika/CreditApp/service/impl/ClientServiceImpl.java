package ru.axiomatika.CreditApp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.axiomatika.CreditApp.dao.ClientDAO;
import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.model.Client;
import ru.axiomatika.CreditApp.service.ClientService;
import ru.axiomatika.CreditApp.util.mapper.ClientMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;
    private final ClientMapper mapper = ClientMapper.INSTANCE;

    @Transactional
    @Override
    public void create(Client client) {
        clientDAO.save(client);
    }

    @Transactional
    @Override
    public void updateById(Long id, ClientDTO clientDTO) {
        Client updatedClient = mapper.clientDtoToClient(clientDTO);
        updatedClient.setId(clientDTO.getId());
        clientDAO.save(updatedClient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientDAO.delete(id);
    }

    @Override
    @Transactional
    public ClientDTO findById(Long id) {
        return mapper.clientToClientDto(clientDAO.findById(id));
    }

    @Override
    @Transactional
    public List<ClientDTO> findAll() {
        return clientDAO.findAll().stream()
                .map(mapper::clientToClientDto)
                .toList();
    }

    public List<ClientDTO> searchClient(String searchType, String searchQuery) {
        switch (searchType) {
            case "PHONE":
                return findByPhoneNumber(searchQuery);
            case "FIO":
                String[] nameParts = searchQuery.split(" ");
                if (nameParts.length == 3) {
                    return findByFIO(nameParts[0], nameParts[1], nameParts[2]);
                } else {
                    return List.of();
                }
            case "PASSPORT":
                return findByPassportData(searchQuery);
            default:
                return List.of();
        }
    }

    @Override
    public List<ClientDTO> findByPhoneNumber(String phoneNumber) {
        return clientDAO.findByPhoneNumber(phoneNumber).stream()
                .map(mapper::clientToClientDto)
                .toList();
    }

    @Override
    public List<ClientDTO> findByFIO(String firstName, String middleName, String lastName) {
        return clientDAO.findByFIO(firstName, lastName, middleName).stream()
                .map(mapper::clientToClientDto)
                .toList();
    }

    @Override
    public List<ClientDTO> findByPassportData(String passportData) {
        return clientDAO.findByPassportData(passportData).stream()
                .map(mapper::clientToClientDto)
                .toList();
    }
}
