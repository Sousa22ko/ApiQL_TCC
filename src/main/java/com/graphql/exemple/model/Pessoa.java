package com.graphql.exemple.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql.exemple.util.CustomDateDeserializer;
import com.graphql.exemple.util.DateFormat;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "id_pessoa"))
public class Pessoa extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * Nome da pessoa
	 */
	@NotNull(message = "Nome vazio.")
	private String nome;

	/**
	 * Email da pessoa
	 */
	@NotNull(message = "E-mail vazio.")
	@Email
	@Column(unique = true)
	private String email;

	/**
	 * Telefone Residencial
	 */
	private String telefoneResidencial;

	/**
	 * Telefone Celular
	 */
	private String telefoneCelular;

	/**
	 * Data de nascimento da pessoa
	 */
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = DateFormat.DATE, timezone = DateFormat.TIMEZONE)
	private Date dataNascimento;

	/**
	 * CPF da pessoa
	 */
	@CPF
	@NotNull(message = "CPF da Pessoa vazio.")
	@Column(unique = true)
	private String cpf;

	/**
	 * Endereço da pessoa
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Endereco.class)
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	/**
	 * Status do cadastro da Pessoa Física. Exs.: Cadastro pendente = False -
	 * Cadastro finalizado = True.
	 */
	@NotNull(message = "Status do cadastro do Colaborador vazio.")
	private Boolean statusCadastro;

	/**
	 * Referencia ao vinculo do colaborador
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_colaborador")
	private List<Vinculo> listVinculo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getStatusCadastro() {
		return statusCadastro;
	}

	public void setStatusCadastro(Boolean statusCadastro) {
		this.statusCadastro = statusCadastro;
	}

	public List<Vinculo> getListVinculo() {
		return listVinculo;
	}

	public void setListVinculo(List<Vinculo> listVinculo) {
		this.listVinculo = listVinculo;
	}
}