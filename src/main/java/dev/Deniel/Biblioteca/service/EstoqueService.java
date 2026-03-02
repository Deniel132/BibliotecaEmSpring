package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.dto.EstoqueDTO;
import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.EstoqueModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

	private final EstoqueRepository estoqueRepository;
	private final LivroService livroService;


	public EstoqueService(EstoqueRepository estoqueRepository, LivroService livroService) {
		this.estoqueRepository = estoqueRepository;
		this.livroService = livroService;
	}



	public EstoqueModel save(EstoqueDTO estoqueDTO){
		EstoqueModel estoqueModel = new EstoqueModel();

		estoqueModel.setTituloDoLivro(estoqueDTO.getTituloDoLivro());
		estoqueModel.setAutor(estoqueDTO.getAutor());
		estoqueModel.setDataDePublicacao(estoqueDTO.getDataDePublicacao());
		estoqueModel.setQuantidade(estoqueDTO.getQuantidade());

		List<LivroModel> listaDeLivros = livrosNoEstoque(estoqueDTO.getIdLivros());

		estoqueModel.setQuantidade(listaDeLivros.size());
		estoqueModel.setLivros(listaDeLivros);


		return this.estoqueRepository.save(estoqueModel);
	}

	public void delete(Long id){
		this.estoqueRepository.deleteById(id);
	}

	public List<EstoqueModel> getAll(){return estoqueRepository.findAll();}

	public List<LivroModel> livrosNoEstoque(List<Long> ids){
		return livroService.getAll().stream()
				.filter(livro -> ids.contains(livro.getId()))
				.toList();
	}

	public EstoqueModel getById(Long id){
		EstoqueModel estoqueModel = this.estoqueRepository.findById(id).orElse(null);
		if (estoqueModel == null) {
			throw new EntityNotFoundException("estoque nao encontrado");
		}else{
			return estoqueModel;
		}
	}

	public EstoqueModel getByIdLivro(Long id){
		LivroModel livroModel = livroService.getById(id);
		EstoqueModel estoqueModel = this.estoqueRepository.findAll().stream().filter(estoqueModel1 -> estoqueModel1.getLivros().contains(livroModel)).findAny().orElse(null);
		if (estoqueModel == null) {
			throw new EntityNotFoundException("estoque nao encontrado");
		}else{
			return estoqueModel;
		}
	}





}
