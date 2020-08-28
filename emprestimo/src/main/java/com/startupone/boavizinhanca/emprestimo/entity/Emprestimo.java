package com.startupone.boavizinhanca.emprestimo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_emprestimo")
@IdClass(EmprestimoId.class)
public class Emprestimo {

	@Id
	@Column(name = "id_user_locatario")
	private Integer idUser;
	@Id
	@Column(name = "id_item")
	private Integer idItem;
	@Column(name = "data_inicio")
	private String dataInicio;
	@Column(name = "data_fim")
	private String dataFim;
	private String valor;
	private Integer status;
	private String avaliacao;
	@Transient
	private Item item;

	public Integer getIdUser() {
		return idUser;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public String getValor() {
		return valor;
	}

	public Integer getStatus() {
		return status;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
