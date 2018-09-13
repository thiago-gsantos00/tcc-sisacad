package br.pucminas.sisacad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.sisacad.exception.DadosInvalidosException;
import br.pucminas.sisacad.model.Aluno;
import br.pucminas.sisacad.service.AutenticacaoService;
import br.pucminas.sisacad.util.JsonUtil;

@RestController
public class ControleAutenticacao {

    private final static String ERRO = "Dados de acesso incorretos. Verifique os dados digitados e tente novamente";
	
	@Autowired
	private AutenticacaoService service;
	
	@RequestMapping(value="/autentica", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autenticar(@RequestBody Aluno aluno){
		try {
			Aluno alunoAutenticado = service.autenticar(aluno);
			return new ResponseEntity<>(alunoAutenticado, HttpStatus.OK);
		} catch (DadosInvalidosException die) {
			return new ResponseEntity<>(JsonUtil.createResponse("response", ERRO), HttpStatus.UNAUTHORIZED);
		}
	}
	
}
