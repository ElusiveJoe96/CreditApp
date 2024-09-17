package ru.axiomatika.CreditApp.service;

import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.dto.RequestDTO;

import java.util.List;

public interface RequestService {

    RequestDTO create(ClientDTO clientDTO);

    List<RequestDTO> getAll();

    List<RequestDTO> getByStatus(boolean status);

    RequestDTO getById(Long id);
}
