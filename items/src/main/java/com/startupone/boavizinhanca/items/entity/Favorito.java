package com.startupone.boavizinhanca.items.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "tb_interesse_publicacao")
@IdClass(FavoritoId.class)
public class Favorito {
	
	@Id
	@Column(name = "id_user_locatario")
	private Integer idUser;
	@Id
	@Column(name = "id_item_publicado")
	private Integer idItem;
	
	@Column(name = "data_interesse")
	private String dataInteresse;

	public Integer getIdUser() {
		return idUser;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public String getDataInteresse() {
		return dataInteresse;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setDataInteresse(String dataInteresse) {
		this.dataInteresse = dataInteresse;
	}

}
