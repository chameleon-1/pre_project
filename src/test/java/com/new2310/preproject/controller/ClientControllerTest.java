package com.new2310.preproject.controller;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientController clientController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void testGetAllClients() throws Exception {
        ClientsINFO clientsINFO = new ClientsINFO();
        List<ClientsINFO> clients = new ArrayList<>();
        clients.add(clientsINFO);
        when(clientService.getAllClients()).thenReturn(clients);
        mockMvc.perform(get("/api/v1/clients").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testGetClientById() throws Exception {
        int id = 1;
        ClientsINFO client = new ClientsINFO();
        client.setId(id);
        client.setName("testName");
        client.setMiddleName("testMiddleName");
        client.setSurname("testSurname");
        client.setPhone("123456");
        client.setBirthday(LocalDate.of(1999, 11, 22));
        client.setAge(25);

        when(clientService.getClients(id)).thenReturn(client);

        mockMvc.perform(get("/api/v1/clients/{id}", id).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("testName"))
                .andExpect(jsonPath("$.middleName").value("testMiddleName"))
                .andExpect(jsonPath("$.surname").value("testSurname"))
                .andExpect(jsonPath("$.phone").value("123456"))
//                .andExpect(jsonPath("$.birthday").value(LocalDate.of(1999, 11, 22)))
                .andExpect(jsonPath("$.age").value(25));

        verify(clientService, times(1)).getClients(id);
    }
}
