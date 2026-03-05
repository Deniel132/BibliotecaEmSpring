package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.repository.ClienteRepository;
import dev.Deniel.Biblioteca.repository.EmprestimoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final EmprestimoRepository emprestimoRepository;


	public ClienteService(ClienteRepository clienteRepository,EmprestimoRepository emprestimoRepository) {
		this.clienteRepository = clienteRepository;
		this.emprestimoRepository = emprestimoRepository;
	}


	public ClienteModel save(ClienteModel clienteModel){return this.clienteRepository.save(clienteModel); }

	@Transactional
	public void delete(Long id){
		if (emprestimoRepository.findAll().stream().anyMatch(emprestimoModel ->
				emprestimoModel.getCliente().equals(getById(id)))){

			inativarUsuario(id);
		}else {
			this.clienteRepository.deleteById(id);
		}
	}

	@Transactional
	public void deleteAll(){ getAll().forEach(clienteModel -> delete(clienteModel.getId()));}

	public List<ClienteModel> getAll(){return clienteRepository.findAll();}

	public ClienteModel getById(Long id){
		ClienteModel clienteModel = this.clienteRepository.findById(id).orElse(null);
		if (clienteModel == null) {
			throw new EntityNotFoundException("Cliente nao encontrado");
		}else{
			return clienteModel;
		}
	}


	public ClienteModel alterarCliente(Long id,ClienteModel cliente){
		ClienteModel clienteAntigo = getById(id);

		clienteAntigo.setNome(cliente.getNome());
		clienteAntigo.setEmail(cliente.getEmail());
		clienteAntigo.setDataDoCadastro(cliente.getDataDoCadastro());
		clienteAntigo.setStatus(cliente.isStatus());

		return save(clienteAntigo);
	}

	public void inativarUsuario(Long id){
		getById(id).setStatus(false);
	}

	@Transactional
	public ClienteModel reativarUsuario(Long id){
		getById(id).setStatus(true);
		return getById(id);
	}


}
