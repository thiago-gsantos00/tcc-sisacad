package br.pucminas.sisacad.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.pucminas.sisacad.exception.DadosInvalidosException;
import br.pucminas.sisacad.model.Aluno;

@Repository
public class AlunoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	/**
	 * 
	 * Método responsável por fazer a verificação de dados de autenticação de um aluno.
	 * 
	 * @param aluno com os atributos e-mail e senha preeenchidos. 
	 * @return Objeto do tipo Aluno com informações cadastradas no banco de dados.
	 * @throws NoResultException caso não seja encontrado nenhum aluno com o e-mail e senha informado
	 * @throws DadosInvalidosException 
	 */
	public Aluno autenticarAluno(Aluno aluno) throws DadosInvalidosException{
		try{
			return (Aluno) manager.createQuery("from Aluno a where a.email like :email and a.senha like :senha")
					.setParameter("email", aluno.getEmail())
					.setParameter("senha", aluno.getSenha())
					.getSingleResult();
		} catch (NoResultException nre){
			throw new DadosInvalidosException();
		}
	}
}
