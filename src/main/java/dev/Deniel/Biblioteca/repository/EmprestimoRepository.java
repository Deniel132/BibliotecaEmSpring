package dev.Deniel.Biblioteca.repository;

import dev.Deniel.Biblioteca.model.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<EmprestimoModel,Long> {
}
