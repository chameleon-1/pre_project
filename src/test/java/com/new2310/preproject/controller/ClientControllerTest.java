package com.new2310.preproject.controller;

import com.new2310.preproject.model.ClientsINFO;
import com.new2310.preproject.service.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
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
    void getClientById() throws Exception {
        ClientsINFO client = new ClientsINFO();
        client.setId(5);
        client.setName("semen");
        client.setMiddleName("semenovich");
        client.setSurname("semenov");
        client.setPhone("2345678");
        client.setAge(20);

        when(clientService.getClients(5)).thenReturn(client);
        mockMvc.perform(get("/api/v1/client/{id}", 5))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("semen"))
                .andExpect(jsonPath("$.middleName").value("semenovich"))
                .andExpect(jsonPath("$.surname").value("semenov"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.phone").value("2345678"));
        verify(clientService, times(1)).getClients(5);
    }

    @Test
    void getAllClients() throws Exception {
        ClientsINFO client1 = new ClientsINFO();
        client1.setId(5);
        client1.setName("semen");
        client1.setMiddleName("semenovich");

        ClientsINFO client2 = new ClientsINFO();
        client2.setId(3);
        client2.setName("ibragim");
        client2.setMiddleName("ibragimovich");

        List<ClientsINFO> clients = Arrays.asList(client1, client2);

        when(clientService.getAllClients()).thenReturn(clients);
        mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk());
        Assertions.assertEquals(2, clients.size());
        Assertions.assertEquals("semen", clients.get(0).getName());
        Assertions.assertEquals("semenovich", clients.get(0).getMiddleName());
        Assertions.assertEquals("ibragim", clients.get(1).getName());
        Assertions.assertEquals("ibragimovich", clients.get(1).getMiddleName());
        verify(clientService, times(1)).getAllClients();
    }
}
