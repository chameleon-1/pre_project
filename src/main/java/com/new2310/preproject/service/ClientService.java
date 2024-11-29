package com.new2310.preproject.service;

import com.new2310.preproject.model.ClientsINFO;

import java.util.List;

public interface ClientService {
    List<ClientsINFO> getAllClients();

    ClientsINFO getClients(int id);

    void convertAndSaveDto();
}
