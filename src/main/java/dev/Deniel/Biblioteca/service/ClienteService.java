package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;


	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}


	public ClienteModel save(ClienteModel clienteModel){return this.clienteRepository.save(clienteModel); }

	public void delete(Long id){this.clienteRepository.deleteById(id);}

	public List<ClienteModel> getAll(){return clienteRepository.findAll();}

	public ClienteModel getById(Long id){
		ClienteModel clienteModel = this.clienteRepository.findById(id).orElse(null);
		if (clienteModel == null) {
			throw new EntityNotFoundException("Cliente nao encontrado");
		}else{
			return clienteModel;
		}
	}

	public void inativarUsuario(Long id){
		getById(id).setStatus(false);
	}


}
