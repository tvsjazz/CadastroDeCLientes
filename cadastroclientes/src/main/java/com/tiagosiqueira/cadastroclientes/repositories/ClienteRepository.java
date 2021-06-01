package com.tiagosiqueira.cadastroclientes.repositories;

import com.tiagosiqueira.cadastroclientes.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
