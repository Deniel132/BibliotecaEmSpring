package dev.Deniel.Biblioteca.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoDTO {

	private Long idCliente;
	private Long IdLivro;
	private LocalDate dataEmprestimo;
	private LocalDate dateDeDevolucao = null;
}
