package dev.Deniel.Biblioteca.controller;


import dev.Deniel.Biblioteca.dto.GoogleBooksDTO;
import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.service.ClienteService;
import dev.Deniel.Biblioteca.service.GoogleBoksService;
import dev.Deniel.Biblioteca.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@CrossOrigin(origins = "*")
public class LivroController {


	private final LivroService livroService;


	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}

	@PostMapping("/isbn/{numeroIsbn}")
	public LivroModel getLivro(@PathVariable String numeroIsbn) {
		return livroService.saveByISBN(numeroIsbn);
	}

	@GetMapping("/{id}")
	public LivroModel mostrarLivro(@PathVariable() Long id){return livroService.getById(id);}

	@GetMapping
	public List<LivroModel> listarLivros(){return livroService.getAll();}

	@PostMapping
	public LivroModel criarLivro(@RequestBody LivroModel livro){return this.livroService.save(livro);}

	@DeleteMapping("/deletar/{id}")
	public void deletarLivro(@PathVariable() Long id){this.livroService.delete(id);}

	@DeleteMapping("/deletartodos")
	public void deletarTodosLivro(){this.livroService.deleteAll();}

	@PutMapping("/alterar/{id}")
	public LivroModel alterarLivros(@PathVariable Long id,@RequestBody LivroModel livroModel){
		return livroService.alterarLivro(id,livroModel);
	}

	@PatchMapping("/reativar/{id}")
	public void reativarLivro(@PathVariable Long id){
		this.livroService.reativarLivro(id);
	}

}
