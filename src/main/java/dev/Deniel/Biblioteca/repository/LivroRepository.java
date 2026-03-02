package dev.Deniel.Biblioteca.repository;

import dev.Deniel.Biblioteca.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel,Long> {
}
