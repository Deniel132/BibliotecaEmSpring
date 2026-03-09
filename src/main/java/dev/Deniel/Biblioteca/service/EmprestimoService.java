package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.dto.EmprestimoDTO;
import dev.Deniel.Biblioteca.dto.EstoqueDTO;
import dev.Deniel.Biblioteca.model.EmprestimoModel;
import dev.Deniel.Biblioteca.model.EstoqueModel;
import dev.Deniel.Biblioteca.model.LivroModel;
import dev.Deniel.Biblioteca.repository.EmprestimoRepository;
import dev.Deniel.Biblioteca.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

	private final EmprestimoRepository emprestimoRepository;
	private final ClienteService clienteService;
	private final LivroService livroService;
	private final EstoqueService estoqueService;
	private final EmailService emailService;

	public EmprestimoService(EmprestimoRepository emprestimoRepository, ClienteService clienteService, LivroService livroService, EstoqueService estoqueService, EmailService emailService) {
		this.emprestimoRepository = emprestimoRepository;
		this.clienteService = clienteService;
		this.livroService = livroService;
		this.estoqueService = estoqueService;
		this.emailService = emailService;
	}


	private EmprestimoModel save(EmprestimoDTO emprestimoDTO){
		EmprestimoModel emprestimoModel = new EmprestimoModel();

		emprestimoModel.setCliente(clienteService.getById(emprestimoDTO.getIdCliente()));
		emprestimoModel.setLivro(livroService.getById(emprestimoDTO.getIdLivro()));
		emprestimoModel.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
		emprestimoModel.setDateDeDevolucao(emprestimoDTO.getDateDeDevolucao());

		emailService.emprestimoPorEmail(emprestimoModel);

		return this.emprestimoRepository.save(emprestimoModel);
	}

	public void delete(Long id){
		this.emprestimoRepository.deleteById(id);
	}


	public EmprestimoModel realisarEmprestimo(EmprestimoDTO emprestimoDTO){
		LivroModel livroModel = livroService.getById(emprestimoDTO.getIdLivro());

		if (livroModel.isEmprestado()){
			throw new EntityNotFoundException("Livro ja emprestado");
		}else{
			livroModel.setEmprestado(true);
			EstoqueModel estoqueModel = estoqueService.getByIdLivro(livroModel.getId());
			estoqueModel.setQuantidade(estoqueModel.getQuantidade() - 1);
		}

		return save(emprestimoDTO);
	}

	@Transactional
	public EmprestimoModel devolver(Long idLivro){
		LivroModel livroModel = livroService.getById(idLivro);
		EmprestimoModel emprestimoModel = getByIdLivro(idLivro);
		EstoqueModel estoqueModel = estoqueService.getByIdLivro(livroModel.getId());
		estoqueModel.setQuantidade(estoqueModel.getQuantidade() + 1);

		livroModel.setEmprestado(false);
		emprestimoModel.setDateDeDevolucao(LocalDate.now());
		emailService.devolucaoPorEmail(emprestimoModel);
		return emprestimoModel;
	}


	public List<EmprestimoModel> getAll(){return emprestimoRepository.findAll();}

	public EmprestimoModel getById(Long id){
		EmprestimoModel emprestimoModel = this.emprestimoRepository.findById(id).orElse(null);
		if (emprestimoModel == null) {
			throw new EntityNotFoundException("emprestimo nao encontrado");
		}else{
			return emprestimoModel;
		}
	}

	public EmprestimoModel getByIdLivro(Long id){
		EmprestimoModel emprestimoModel = this.emprestimoRepository.findAll().stream().filter(emprestimoModel1 -> emprestimoModel1.getLivro().getId().equals(id)).findAny().orElse(null);
		if (emprestimoModel == null) {
			throw new EntityNotFoundException("Cliente nao encontrado");
		}else{
			return emprestimoModel;
		}
	}


}
