package ru.axiomatika.CreditApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.dto.ContractDTO;
import ru.axiomatika.CreditApp.dto.RequestDTO;
import ru.axiomatika.CreditApp.service.ContractService;
import ru.axiomatika.CreditApp.service.RequestService;


@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;
    private final ContractService contractService;

    @GetMapping("/new")
    public String newRequest(Model model) {
        model.addAttribute("client", new ClientDTO());
        return "request/create";
    }

    @GetMapping
    public String requests(Model model) {
        model.addAttribute("requests", requestService.getAll());
        return "request/requests";
    }

    @PostMapping
    public String createRequest(@ModelAttribute("client") @Valid ClientDTO clientDTO,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "request/create";
        }

        RequestDTO requestDTO = requestService.create(clientDTO);
        model.addAttribute("request", requestDTO);

        if (requestDTO.isApprovalStatus()) {
            ContractDTO contractDTO = contractService.create(requestDTO);
            model.addAttribute("contract", contractDTO);
            return "redirect:/contracts/" + contractDTO.getId();
        }

        return "client/loan-rejected";
    }
}
