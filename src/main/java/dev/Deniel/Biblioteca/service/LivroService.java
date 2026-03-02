package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.model.ClienteModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

	private final LivroRepository livroRepository;


	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	public LivroModel save(LivroModel livroModel){return this.livroRepository.save(livroModel); }

	public void delete(Long id){this.livroRepository.deleteById(id);}

	public List<LivroModel> getAll(){return livroRepository.findAll();}

	public LivroModel getById(Long id){
		LivroModel livroModel = this.livroRepository.findById(id).orElse(null);
		if (livroModel == null) {
			throw new EntityNotFoundException("Cliente nao encontrado");
		}else{
			return livroModel;
		}
	}





}
