package com.new2310.preproject.jpa;

import com.new2310.preproject.model.ClientsINFO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientsINFO, Integer> {
 }