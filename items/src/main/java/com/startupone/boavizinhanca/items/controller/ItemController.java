package com.startupone.boavizinhanca.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startupone.boavizinhanca.items.entity.Item;
import com.startupone.boavizinhanca.items.service.ItemService;

@RestController
@RequestMapping("/items") 
public class ItemController {

	@Autowired
	private ItemService service;
	
	@PostMapping("/add")
	public Item addItem(@RequestBody Item item) {
		return service.saveItem(item);
	}
	
	@GetMapping("/findAll")
	public List<Item> findAllItems(){
		return service.getItems();
	}
	
	@GetMapping("/findItem/{id}")
	public Item findById(@PathVariable int id){
		return service.getItemById(id);
	}
	
	@GetMapping("/findItemProp/{idProprietario}")
	public List<Item> findByIdProprietario(@PathVariable int idProprietario){
		return service.getItemsByIdProprietario(idProprietario);
	}
	
	@PutMapping("/update")
	public Item updateItem(@RequestBody Item item) {
		return service.updateItem(item);
	}
	
	@DeleteMapping
	public String deleteItem(@PathVariable int id) {
		return service.deleteItem(id);
	}
}
