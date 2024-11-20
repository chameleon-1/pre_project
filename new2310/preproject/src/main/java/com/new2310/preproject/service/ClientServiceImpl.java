package com.new2310.preproject.service;

import com.new2310.preproject.dto.Client;
import com.new2310.preproject.mapper.ClientMapper;
import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.repositiry.ClientRepository;
import com.new2310.preproject.repositiry.ClientRepository2;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientRepository2 clientRepository2;

    private ClientMapper clientMapper;

    @Override
    public List<ClientsINFO> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void convertAndSaveDto() {
        LocalDate today = LocalDate.now();
        List<ClientsINFO> list1 = clientRepository.findAll();
        List<Client> list2 = clientMapper.listClientsINFOToDTO(list1);
        List<Client> list3 = list2.stream()
                .filter(e -> e.getBirthday().getMonth() == (today.getMonthValue() - 1))
                                                                //getMonth начинает отсчет с нуля
                .filter(e -> e.getPhone().endsWith("7"))      //номер телефона оканчивающегося на 7
                .collect(Collectors.toList());
        clientRepository2.saveAll(list3);
    }

    @Override
    public ClientsINFO getClient(int id) {
        return clientRepository.getOne(id);
    }
}
