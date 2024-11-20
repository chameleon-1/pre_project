package com.new2310.preproject.mapper;

import com.new2310.preproject.dto.Client;
import com.new2310.preproject.model.ClientsINFO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/*
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-18T14:50:17+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
*/
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client clientsINFOToDTO(ClientsINFO clientsINFO) {
        if ( clientsINFO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientsINFO.getId() );
        client.setPhone( clientsINFO.getPhone() );
        client.setBirthday( clientsINFO.getBirthday() );

        client.setFullName( fullname(clientsINFO.getName(), clientsINFO.getMiddleName() ) );

        return client;
    }

    @Override
    public List<Client> listClientsINFOToDTO(List<ClientsINFO> listClientsINFO) {
        if ( listClientsINFO == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( listClientsINFO.size() );
        for ( ClientsINFO clientsINFO : listClientsINFO ) {
            list.add( clientsINFOToDTO( clientsINFO ) );
        }

        return list;
    }
}
