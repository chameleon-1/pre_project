package com.new2310.preproject.controller;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @GetMapping(value = "/clients")
//    public ResponseEntity<List<Client>> getAllClients() {
    public List<ClientsINFO> getAllClients() {
        List<ClientsINFO> allClients = clientService.getAllClients();
        return allClients;
    }

    @GetMapping("/clients_with_filter")
    public List<ClientsINFO> getClientsWithFilter(){
        List <ClientsINFO> clientsWithFilter = clientService.getClientWithFilter();
        return clientsWithFilter;
    }

    @GetMapping(value = "/clients/{id}")
    public ClientsINFO getClient(@PathVariable int id) {
        ClientsINFO client = clientService.getClient(id);
        return client;
    }
}
