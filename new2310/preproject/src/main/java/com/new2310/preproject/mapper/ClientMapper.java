package com.new2310.preproject.mapper;


import com.new2310.preproject.dto.Client;
import com.new2310.preproject.model.ClientsINFO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
        (componentModel = "spring",
                unmappedTargetPolicy = ReportingPolicy.IGNORE,
                injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    //    @Mapping(target = "age", ignore = true)                   //это все заменяет репортинг полиция
//    @Mapping(target = "middlename", ignore = true)
//    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "fullName", expression =
            "java(fullname(clientsINFO.getName(), clientsINFO.getMiddleName() ))")
    Client clientsINFOToDTO(ClientsINFO clientsINFO);

    List<Client> listClientsINFOToDTO(List<ClientsINFO> listClientsINFO);

    default String fullname(String name, String middleName) {
        return name + " " + middleName;
    }
}
