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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.startupone.boavizinhanca.items.entity.Item;
import com.startupone.boavizinhanca.items.service.ItemService;

@CrossOrigin(maxAge = 3600) 
@RestController
@RequestMapping("/items") 
public class ItemController {

	@Autowired
	private ItemService service;
	
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888"},maxAge=4800, allowCredentials="false")
	@PostMapping("/publish")
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
	
	@GetMapping("/findItemProprietario/{id}")
	public List<Item> findByIdProprietario(@PathVariable int id){
		return service.getItemsByIdProprietario(id);
	}
	
	@PutMapping("/update")
	public Item updateItem(@RequestBody Item item) {
		return service.updateItem(item);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteItem(@PathVariable int id) {
		return service.deleteItem(id);
	}
}
