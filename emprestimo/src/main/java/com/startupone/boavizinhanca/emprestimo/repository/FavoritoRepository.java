package com.startupone.boavizinhanca.emprestimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startupone.boavizinhanca.emprestimo.entity.Favorito;
import com.startupone.boavizinhanca.emprestimo.entity.FavoritoId;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {

	List<Favorito> findByIdUser(Integer id);
	
}
