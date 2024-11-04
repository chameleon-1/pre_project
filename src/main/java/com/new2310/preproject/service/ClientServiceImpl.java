package com.new2310.preproject.service;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.repositiry.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Override
    public List<ClientsINFO> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<ClientsINFO> getClientWithFilter() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List <ClientsINFO> list = clientRepository.findAll();
        List<ClientsINFO> list2 = list.stream()          //перевод др в формат даты, получение месяца и сравнивание с текущим
                .filter(e->LocalDate.parse(e.getBirthday(),dtf).getMonthValue()==(today.getMonthValue()))
                .filter(e->e.getPhone().endsWith("7"))         //номер телефона оканчивающегося на 7
                .collect(Collectors.toList());
        return list2;
    }

    @Override
    public ClientsINFO getClient(int id) {
        return clientRepository.getOne(id);
    }
}
