package com.new2310.preproject.controller;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClientController {
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientsINFO>> getAllClients(){
        List<ClientsINFO> listAllClients = clientService.getAllClients();
        return ResponseEntity.ok(listAllClients);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientsINFO> getClient (@PathVariable int id){
       ClientsINFO client = clientService.getClients(id);
       return ResponseEntity.ok(client);
    }

    @Scheduled(fixedRateString = "${task.fixed.rate.millis}")
    public void convertAndSaveDto() {
        clientService.convertAndSaveDto();
    }
}
