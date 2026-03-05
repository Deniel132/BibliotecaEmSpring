package dev.Deniel.Biblioteca.controller;


import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.service.ClienteService;
import dev.Deniel.Biblioteca.service.LivroService;
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

	@GetMapping("/{id}")
	public LivroModel mostrarLivro(@PathVariable() Long id){return livroService.getById(id);}

	@GetMapping
	public List<LivroModel> listarLivros(){return livroService.getAll();}

	@PostMapping
	public LivroModel criarLivro(@RequestBody LivroModel livro){return this.livroService.save(livro);}

	@DeleteMapping("/deletarconta/{id}")
	public void deletarLivro(@PathVariable() Long id){this.livroService.delete(id);}


}
