package br.pucminas.sisacad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import br.pucminas.sisacad.dao.AlunoDAO;
import br.pucminas.sisacad.exception.DadosInvalidosException;
import br.pucminas.sisacad.model.Aluno;

@Service
public class AutenticacaoService {
	
	@Autowired
	private AlunoDAO dao;
	
	/**
	 * 
	 * Método responsável por fazer a verificação de dados de autenticação de um aluno.
	 * 
	 * @param aluno aluno com os atributos e-mail e senha preeenchidos.
	 * @return Objeto do tipo Aluno com informações cadastradas no banco de dados.
	 * @throws DadosInvalidosException Caso os dados de autenticação estiverem incorretos.
	 */
	public Aluno autenticar(Aluno aluno) throws DadosInvalidosException{
		if(Strings.isNullOrEmpty(aluno.getEmail()) 
				|| Strings.isNullOrEmpty(aluno.getSenha())) 
			throw new DadosInvalidosException();
		else 
			return dao.autenticarAluno(aluno);
	}
	
}
