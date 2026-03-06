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


	private String titulo;
	private boolean emprestado = false;
	private String statusDeUso;
	private String LinkImagem;
	private String autor;
	private boolean ativo = true;




}
