package com.new2310.preproject.repositiry;

import com.new2310.preproject.dto.Client;
import org.springframework.data.jpa.repository.JpaRepository;

// для работы со второй таблицей
public interface ClientRepository2 extends JpaRepository<Client, Integer> {
}
