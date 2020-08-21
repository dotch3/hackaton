package com.startupone.boavizinhanca.items.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
		List<Item> items = repository.findAll();
		calcularPeriodoPublicacao(items);
		return items;
	}

	private void calcularPeriodoPublicacao(List<Item> items) {
		LocalDate dateHoje = LocalDate.now();
		for(Item item : items) {
			if(item.getDataPublicacao() != null && (item.getDataPublicacao().length() == 10)) {				
				String dataPublicacao = item.getDataPublicacao();
				String ano = dataPublicacao.substring(6);
				String mes = dataPublicacao.substring(3, 5);
				String dia = dataPublicacao.substring(0, 2);
				LocalDate datePublicacao = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
				Period periodo = Period.between(datePublicacao, dateHoje);
				item.setDataPublicacao(periodo.getDays() == 0 ? "Hoje" : ("Há " + periodo.getDays() + (periodo.getDays() == 1 ? " dia" : " dias")));
			}
		}
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
		Item existingItem = repository.findById(item.getIdItem()).orElse(null);
		return existingItem != null ? repository.save(item) : null;
	}
}
