package com.new2310.preproject.controller;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClientController {
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public List<ClientsINFO> getAllClients() {
        List<ClientsINFO> allClients = clientService.getAllClients();
        return allClients;
    }

    @GetMapping(value = "/clients/{id}")
    public ClientsINFO getClient(@PathVariable int id) {
        ClientsINFO client = clientService.getClient(id);
        return client;
    }

    @Scheduled(fixedRateString = "${task.fixed.rate.millis}")
    @PostMapping("/test")
    public void test() {
        clientService.convertAndSaveDto();
    }
}
