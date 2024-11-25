package com.new2310.preproject.repositiry;

import com.new2310.preproject.model.ClientsINFO;
import org.springframework.data.jpa.repository.JpaRepository;

//репозиторий для взаимодействия с бд
public interface ClientRepository extends JpaRepository<ClientsINFO, Integer> {
}