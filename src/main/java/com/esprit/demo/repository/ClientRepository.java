package com.esprit.demo.repository;

import com.esprit.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client,Long> {
}
