package com.startupone.boavizinhanca.items.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item_publicado")
public class Item {
	
	@Id
	@GeneratedValue
	@Column(name = "id_item")
	private int idItem;
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
	@Column(name = "data_sugestao_devolucao")
	private String dataSugestaoDevolucao;
	

}
