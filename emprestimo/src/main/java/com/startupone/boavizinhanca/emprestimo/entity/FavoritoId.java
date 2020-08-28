package com.startupone.boavizinhanca.emprestimo.entity;

import java.io.Serializable;

public class FavoritoId implements Serializable {

	private static final long serialVersionUID = 3467054586707201596L;
	
	private Integer idUser;
	private Integer idItem;
	
	public Integer getIdUser() {
		return idUser;
	}
	public Integer getIdItem() {
		return idItem;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoritoId other = (FavoritoId) obj;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
}
