package com.startupone.boavizinhanca.items.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_item_publicado")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_item")
	private Integer idItem;
	@Column(name = "id_user_proprietario")
	private int idUserProprietario;
	private String nome;
	@Column(name = "quantidade_disponivel")
	private int quantidadeDisponivel;
	private String valor;
	@Column(name = "tipo_valor")
	private int tipoValor;
	private String tags;
	private String descricao;
	private byte[] foto;
	private String observacao;
	@Column(name = "data_publicacao")
	private String dataPublicacao;
	
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	public int getIdUserProprietario() {
		return idUserProprietario;
	}
	public void setIdUserProprietario(int idUserProprietario) {
		this.idUserProprietario = idUserProprietario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getTipoValor() {
		return tipoValor;
	}
	public void setTipoValor(int tipoValor) {
		this.tipoValor = tipoValor;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
}
