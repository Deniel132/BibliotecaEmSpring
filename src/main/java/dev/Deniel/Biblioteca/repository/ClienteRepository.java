package dev.Deniel.Biblioteca.repository;

import dev.Deniel.Biblioteca.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel,Long> {
}
