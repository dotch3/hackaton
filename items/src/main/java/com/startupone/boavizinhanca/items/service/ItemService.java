package com.startupone.boavizinhanca.items.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupone.boavizinhanca.items.entity.Item;
import com.startupone.boavizinhanca.items.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;
	
	
	public Item saveItem(Item item) {
		return repository.save(item);
	}
	
	public List<Item> getItems(){
		return repository.findAll();
	}
	
	public Item getItemById(int id){
		return repository.findById(id).orElse(null);
	}
	
	public List<Item> getItemsByIdProprietario(int id){
		return repository.findByIdUserProprietario(id);
	}
	
	public String deleteItem(int id) {
		repository.deleteById(id);
		return "Item removido!"; 
	}
	
	public Item updateItem(Item item) {
		Item existingItem = repository.findById(item.getIdItem()).orElse(item);
		BeanUtils.copyProperties(item, existingItem);
		return repository.save(existingItem);
	}
}
