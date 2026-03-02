package dev.Deniel.Biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class LivroModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private Long idNoEstoque;
	private boolean emprestado = false;
	private String statusDeUso;




}
