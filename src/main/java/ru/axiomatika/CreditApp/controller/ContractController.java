package ru.axiomatika.CreditApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.axiomatika.CreditApp.dto.ContractDTO;
import ru.axiomatika.CreditApp.service.ContractService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    @GetMapping
    public String contracts(Model model) {
        model.addAttribute("contracts", contractService.findAll());
        return "contract/contracts";
    }

    @PostMapping("/{id}/sign")
    public String signContract(@PathVariable Long id, Model model) {
        ContractDTO contractDTO = contractService.signContract(id);
        model.addAttribute("contract", contractDTO);
        return "contract/contract";
    }

    @GetMapping("/{id}")
    public String viewContract(@PathVariable Long id, Model model) {
        ContractDTO contractDTO = contractService.getById(id);
        model.addAttribute("contract", contractDTO);
        return "contract/contract";
    }
}
