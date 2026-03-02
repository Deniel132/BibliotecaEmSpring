package dev.Deniel.Biblioteca.service;

import dev.Deniel.Biblioteca.model.EmprestimoModel;
import dev.Deniel.Biblioteca.model.EstoqueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void emprestimoPorEmail(EmprestimoModel emprestimoModel) {
		// Validação preventiva: evita NullPointerException se o e-mail não estiver cadastrado
		if (emprestimoModel.getCliente() == null || emprestimoModel.getCliente() .getEmail() == null) {
			System.err.println("Impossível enviar e-mail: Cliente sem endereço de e-mail.");
			return;
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sua-biblioteca@exemplo.com"); // É boa prática definir o remetente
		message.setTo(emprestimoModel.getCliente().getEmail());
		message.setSubject("Empréstimo Realizado: " + emprestimoModel.getLivro().getTitulo());
		message.setText("Olá " + emprestimoModel.getCliente().getNome() + ",\n\n" +
				"Você pegou o livro: " + emprestimoModel.getLivro().getTitulo() + "\n" +
				"Data de Emprestimo: " + emprestimoModel.getDataEmprestimo() + ".\n\n" +
				"Aproveite a leitura!");

		mailSender.send(message);
	}

	public void devolucaoPorEmail(EmprestimoModel emprestimoModel) {
		if (emprestimoModel.getCliente() == null || emprestimoModel.getCliente() .getEmail() == null) return;

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sua-biblioteca@exemplo.com");
		message.setTo(emprestimoModel.getCliente().getEmail());
		message.setSubject("Devolução Confirmada");
		message.setText("Olá " + emprestimoModel.getCliente().getNome()+ ",\n" +
				"O livro " + emprestimoModel.getLivro().getTitulo() + " foi devolvido com sucesso!");

		mailSender.send(message);
	}







}
