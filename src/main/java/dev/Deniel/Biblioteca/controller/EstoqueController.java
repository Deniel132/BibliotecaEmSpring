package dev.Deniel.Biblioteca.controller;

import dev.Deniel.Biblioteca.dto.EstoqueDTO;
import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.EstoqueModel;
import dev.Deniel.Biblioteca.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@CrossOrigin(origins = "*")
public class EstoqueController {

	private final EstoqueService estoqueService;

	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}


	@GetMapping("/{id}")
	public EstoqueModel mostrarEstoque(@PathVariable() Long id){return estoqueService.getById(id);}

	@GetMapping
	public List<EstoqueModel> listarEstoque(){return estoqueService.getAll();}

	@PostMapping
	public EstoqueModel addEstoque(@RequestBody EstoqueDTO estoqueDTO){return this.estoqueService.save(estoqueDTO);}

	@DeleteMapping("/deletar/{id}")
	public void deletarEstoque(@PathVariable() Long id){this.estoqueService.delete(id);}
}
