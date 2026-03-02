package dev.Deniel.Biblioteca.repository;

import dev.Deniel.Biblioteca.model.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueModel,Long> {
}
