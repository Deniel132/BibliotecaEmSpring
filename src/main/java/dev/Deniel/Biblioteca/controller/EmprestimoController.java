package dev.Deniel.Biblioteca.controller;

import dev.Deniel.Biblioteca.dto.EmprestimoDTO;
import dev.Deniel.Biblioteca.dto.EstoqueDTO;
import dev.Deniel.Biblioteca.model.EmprestimoModel;
import dev.Deniel.Biblioteca.model.EstoqueModel;
import dev.Deniel.Biblioteca.service.EmprestimoService;
import dev.Deniel.Biblioteca.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@CrossOrigin(origins = "*")
public class EmprestimoController {


	private final EmprestimoService emprestimoService;

	public EmprestimoController(EmprestimoService emprestimoService) {
		this.emprestimoService = emprestimoService;
	}


	@GetMapping("/{id}")
	public EmprestimoModel mostarEmprestimo(@PathVariable() Long id){return emprestimoService.getById(id);}

	@GetMapping
	public List<EmprestimoModel> listarEmprestimos(){return emprestimoService.getAll();}

	@PostMapping
	public EmprestimoModel realisarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO){return this.emprestimoService.realisarEmprestimo(emprestimoDTO);}


	@PostMapping("/devolver/{idLivro}")
	public EmprestimoModel devolver(@PathVariable Long idLivro){return this.emprestimoService.devolver(idLivro);}

	@DeleteMapping("/deletar/{id}")
	public void deletarEstoque(@PathVariable() Long id){this.emprestimoService.delete(id);}





}
