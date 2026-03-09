package dev.Deniel.Biblioteca.dto;


import lombok.Data;


import java.util.List;

@Data
public class EstoqueDTO {

	private String tituloDoLivro;

	private List<Long> idLivros;


	private Integer quantidade = 0;
	private String autor;

}
