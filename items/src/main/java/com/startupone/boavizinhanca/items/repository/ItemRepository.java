package com.startupone.boavizinhanca.items.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startupone.boavizinhanca.items.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByIdUserProprietario(int id);

}
