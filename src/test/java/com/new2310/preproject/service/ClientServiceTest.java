package com.new2310.preproject.service;

import com.new2310.preproject.dto.Client;
import com.new2310.preproject.jpa.ClientRepository;
import com.new2310.preproject.jpa.ClientRepository2;
import com.new2310.preproject.mapper.ClientMapper;
import com.new2310.preproject.model.ClientsINFO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientRepository2 clientRepository2;
    @Spy
    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Test
    public void testGetAllClient() {
        List<ClientsINFO> clients = Arrays.asList(new ClientsINFO(), new ClientsINFO());
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        List<ClientsINFO> result = clientServiceImpl.getAllClients();
        Assertions.assertEquals(2, result.size());
        Mockito.verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetClientById() {
        ClientsINFO client = new ClientsINFO();
        client.setName("testName");
        client.setId(1);
        Mockito.when(clientRepository.getReferenceById(1)).thenReturn(client);
        ClientsINFO result = clientServiceImpl.getClients(1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals("testName", result.getName());
        Mockito.verify(clientRepository, times(1)).getReferenceById(1);
    }

    @Test
    public void testConvertToDtoAndSave() {
        LocalDate today = LocalDate.now();
        ClientsINFO clientsINFO1 = new ClientsINFO();
        clientsINFO1.setId(1);
        clientsINFO1.setName("testName1");
        clientsINFO1.setMiddleName("testMiddleName1");
        clientsINFO1.setBirthday(LocalDate.of(1999, today.getMonth(), 22));
        clientsINFO1.setPhone("4567");
        ClientsINFO clientsINFO2 = new ClientsINFO();
        clientsINFO2.setId(2);
        clientsINFO2.setName("testName2");
        clientsINFO2.setMiddleName("testMiddleName2");
        clientsINFO2.setBirthday(LocalDate.of(1988, 10, 20));
        clientsINFO2.setPhone("1234");
        List<ClientsINFO> listClientsInfo = Arrays.asList(clientsINFO1, clientsINFO2);

        Client client1 = clientMapper.clientsINFOToDTO(clientsINFO1);
        Client client2 = clientMapper.clientsINFOToDTO(clientsINFO2);
        List<Client> listDto = Arrays.asList(client1, client2);

        Mockito.when(clientRepository.findAll()).thenReturn(listClientsInfo);
        Mockito.when(clientMapper.listClientsINFOToDTO(listClientsInfo)).thenReturn(listDto);

        clientServiceImpl.convertAndSaveDto();

        List<Client> listWithFilterClients = listDto.stream()
                .filter(e -> e.getBirthday().getMonth() == today.getMonthValue() - 1)
                .filter(e -> e.getPhone().endsWith("7"))
                .collect(Collectors.toList());

        Mockito.verify(clientRepository2, times(1)).saveAll(listWithFilterClients);

        Assertions.assertNotNull(listDto);
        Assertions.assertNotNull(listWithFilterClients);
        Assertions.assertEquals(1, listDto.get(0).getId());
        Assertions.assertEquals("testName1 testMiddleName1", listDto.get(0).getFullName());
    }
}
