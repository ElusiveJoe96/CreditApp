package ru.axiomatika.CreditApp.service;

import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.model.Client;

import java.util.List;

public interface ClientService {

    void create(Client client);

    void updateById(Long id, ClientDTO clientDTO);

    void deleteById(Long id);

    ClientDTO findById(Long id);

    List<ClientDTO> findAll();

    List<ClientDTO> searchClient(String searchType, String searchQuery);

    List<ClientDTO> findByPhoneNumber(String phoneNumber);

    List<ClientDTO> findByFIO(String firstName, String lastName, String middleName);

    List<ClientDTO> findByPassportData(String passportData);
}
