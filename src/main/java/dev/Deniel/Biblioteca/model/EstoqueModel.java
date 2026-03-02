package dev.Deniel.Biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class EstoqueModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tituloDoLivro;

	@OneToMany
	@JoinColumn(name = "estoque_id")
	private List<LivroModel> livros;


	private Integer quantidade = 0;
	private String autor;
	private LocalDate dataDePublicacao;
}
