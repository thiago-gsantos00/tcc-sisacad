package br.pucminas.sisacad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "Aluno")
@Table(name="Aluno")
public class Aluno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="alu_cpf")
	private String cpf;
	@Column(name="alu_nome")
	private String nome;
	@Column(name="alu_endereco")
	private String endereco;
	@Column(name="alu_municipio")
	private String municipio;
	@Column(name="alu_estado")
	private String estado;
	@Column(name="alu_telefone")
	private String telefone;
	@Column(name="alu_email")
	private String email;
	@Column(name="alu_senha")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "Aluno [cpf=" + cpf + ", nome=" + nome + ", endereco=" + endereco + ", municipio=" + municipio
				+ ", estado=" + estado + ", telefone=" + telefone + ", email=" + email + "]";
	}
	
}
