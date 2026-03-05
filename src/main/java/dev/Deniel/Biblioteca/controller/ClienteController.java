package dev.Deniel.Biblioteca.controller;


import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {


	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/{id}")
	public ClienteModel mostrarCliente(@PathVariable() Long id){return clienteService.getById(id);}

	@GetMapping
	public List<ClienteModel> listarClientes(){return clienteService.getAll();}

	@PostMapping
	public ClienteModel criarCliente(@RequestBody ClienteModel cliente){return this.clienteService.save(cliente);}

	@DeleteMapping("/deletarconta/{id}")
	public void deletarCliente(@PathVariable() Long id){this.clienteService.delete(id);}

	@DeleteMapping("/deletarconta")
	public void deletarTodos(){this.clienteService.deleteAll();}

	@PutMapping("/alterar/{id}")
	public ClienteModel alterarClientes(@PathVariable Long id,@RequestBody ClienteModel cliente){
		return clienteService.alterarCliente(id,cliente);
	}

	@PatchMapping("/reativar/{id}")
	public ClienteModel alterarClientes(@PathVariable Long id){
		return clienteService.reativarUsuario(id);
	}



}
