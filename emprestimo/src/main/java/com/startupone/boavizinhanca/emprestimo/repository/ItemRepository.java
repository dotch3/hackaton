package com.startupone.boavizinhanca.emprestimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startupone.boavizinhanca.emprestimo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByIdUserProprietario(int id);
	
	// Search Nome
	List<Item> findByNomeLike(String nome);
	List<Item> findByNomeContains(String nome);
	
	// Search Tags
	List<Item> findByTagsLike(String tags);
	List<Item> findByTagsContains(String tags);
	
	// Search Descricao
	List<Item> findByDescricaoLike(String descricao);
	List<Item> findByDescricaoContains(String descricao);
	
}
