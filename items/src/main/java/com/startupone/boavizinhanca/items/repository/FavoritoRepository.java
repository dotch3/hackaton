package com.startupone.boavizinhanca.items.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startupone.boavizinhanca.items.entity.Favorito;
import com.startupone.boavizinhanca.items.entity.FavoritoId;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

	List<Favorito> findByIdUser(Integer id);
	
}
