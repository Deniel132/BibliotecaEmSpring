package dev.Deniel.Biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class EmprestimoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private ClienteModel cliente;

	@ManyToOne
	@JoinColumn(name = "livro_id")
	private LivroModel livro;

	private LocalDate dataEmprestimo;
	private LocalDate dateDeDevolucao;
}
