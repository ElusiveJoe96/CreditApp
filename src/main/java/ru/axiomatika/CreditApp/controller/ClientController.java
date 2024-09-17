package ru.axiomatika.CreditApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.axiomatika.CreditApp.dto.ClientDTO;
import ru.axiomatika.CreditApp.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/clients";
    }

    @GetMapping("/{id}")
    public String showClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "client/info";
    }

    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "client/edit";
    }

    @PatchMapping("/{id}")
    public String updateClient(@ModelAttribute("client") @Valid ClientDTO updatedClientDTO,
                               BindingResult bindingResult,
                               @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "client/edit";
        }
        clientService.updateById(id, updatedClientDTO);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable long id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }

    @GetMapping("/search")
    public String searchClient(@RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String searchQuery, Model model) {
        if (searchType != null && searchQuery != null) {
            model.addAttribute("clients", clientService.searchClient(searchType, searchQuery));
        } else {
            model.addAttribute("clients", List.of());
        }
        return "client/clients";
    }
}
