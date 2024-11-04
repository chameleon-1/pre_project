package com.new2310.preproject.service;

import com.new2310.preproject.model.ClientsINFO;

import java.util.List;

public interface ClientService {
    List<ClientsINFO> getAllClients();

    List<ClientsINFO> getClientWithFilter();

    ClientsINFO getClient(int id);

}
