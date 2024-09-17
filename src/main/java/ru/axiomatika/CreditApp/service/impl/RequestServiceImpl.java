package ru.axiomatika.CreditApp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.axiomatika.CreditApp.dao.ClientDAO;
import ru.axiomatika.CreditApp.dao.RequestDAO;
import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.dto.RequestDTO;
import ru.axiomatika.CreditApp.model.Client;
import ru.axiomatika.CreditApp.model.Request;
import ru.axiomatika.CreditApp.service.RequestService;
import ru.axiomatika.CreditApp.util.mapper.ClientMapper;
import ru.axiomatika.CreditApp.util.mapper.RequestMapper;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestDAO requestDAO;
    private final ClientDAO clientDAO;


    private final RequestMapper requestMapper = RequestMapper.INSTANCE;
    private final ClientMapper clientMapper = ClientMapper.INSTANCE;


    private final Random random = new Random();


    @Override
    @Transactional
    public RequestDTO create(ClientDTO clientDTO) {
        Client client = clientMapper.clientDtoToClient(clientDTO);
        Request request = createRequestForClient(client);
        clientDAO.save(client);
        requestDAO.save(request);

        return requestMapper.requestToRequestDTO(request);
    }

    private Request createRequestForClient(Client client) {
        return Request.builder()
                .approvalStatus(random.nextDouble() < 0.75)
                .termInDays(random.nextInt(336) + 30)
                .approvedAmount(client.getDesiredCreditAmount())
                .client(client)
                .build();
    }


    @Override
    @Transactional
    public List<RequestDTO> getAll() {
        return requestDAO.findAll().stream()
                .map(requestMapper::requestToRequestDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<RequestDTO> getByStatus(boolean status) {
        return requestDAO.findByStatus(status).stream()
                .map(requestMapper::requestToRequestDTO)
                .toList();
    }

    @Override
    @Transactional
    public RequestDTO getById(Long id) {
        return requestMapper.requestToRequestDTO(requestDAO.findById(id));
    }

}
