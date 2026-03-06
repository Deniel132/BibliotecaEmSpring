package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.dto.GoogleBooksDTO;
import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.repository.EmprestimoRepository;
import dev.Deniel.Biblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LivroService {
	private final LivroRepository livroRepository;
	private final GoogleBoksService googleBoksService;
	private final EmprestimoRepository emprestimoRepository;


	public LivroService(LivroRepository livroRepository, GoogleBoksService googleBoksService, EmprestimoRepository emprestimoRepository) {
		this.livroRepository = livroRepository;
		this.googleBoksService = googleBoksService;
		this.emprestimoRepository = emprestimoRepository;
	}

	public LivroModel saveByISBN(String isbn){
		GoogleBooksDTO.GoogleBooksResponse resultado = googleBoksService.buscarPorIsbn(isbn);

		System.out.println(resultado);


		if (resultado.getItems() == null || resultado.getItems().isEmpty()) {
			throw new EntityNotFoundException("Livro não encontrado");
		}

		GoogleBooksDTO.VolumeInfo item = resultado.getItems().getFirst().getVolumeInfo();
		 LivroModel livroModel = new LivroModel();

		livroModel.setTitulo(item.getTitle());
		livroModel.setAutor(item.getAuthors().getFirst());
		livroModel.setLinkImagem(googleBoksService.getImagemCapa(item));
		livroModel.setStatusDeUso("Livro Por ISBN");

		return save(livroModel);
	}


	@Transactional
	public void deleteAll(){getAll().forEach(livroModel -> delete(livroModel.getId()));}



	public LivroModel save(LivroModel livroModel){return this.livroRepository.save(livroModel); }

	@Transactional
	public void delete(Long id){
		if (emprestimoRepository.findAll().stream().anyMatch(emprestimoModel ->
				emprestimoModel.getLivro().equals(getById(id)))){

			inativarLivro(id);
		}else {
			this.livroRepository.deleteById(id);
		}
	}

	public List<LivroModel> getAll(){return livroRepository.findAll();}

	public LivroModel getById(Long id){
		LivroModel livroModel = this.livroRepository.findById(id).orElse(null);
		if (livroModel == null) {
			throw new EntityNotFoundException("Cliente nao encontrado");
		}else{
			return livroModel;
		}
	}


	private void inativarLivro(Long id){
		LivroModel livroModel = getById(id);
		livroModel.setAtivo(false);
	}

	@Transactional
	public void reativarLivro(Long id){
		LivroModel livroModel = getById(id);
		livroModel.setAtivo(true);
	}

	public LivroModel alterarLivro(Long id, LivroModel livroModel){
		LivroModel livroAntigo = getById(id);

		livroAntigo.setTitulo(livroModel.getTitulo());
		livroAntigo.setAutor(livroModel.getAutor());
		livroAntigo.setStatusDeUso(livroModel.getStatusDeUso());

		return save(livroAntigo);
	}

}
