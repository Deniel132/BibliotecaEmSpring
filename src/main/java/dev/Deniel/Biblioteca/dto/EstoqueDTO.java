package dev.Deniel.Biblioteca.dto;

import dev.Deniel.Biblioteca.model.LivroModel;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EstoqueDTO {

	private String tituloDoLivro;

	private List<Long> idLivros;


	private Integer quantidade = 0;
	private String autor;
	private LocalDate dataDePublicacao;

}
