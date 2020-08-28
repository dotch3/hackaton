package com.startupone.boavizinhanca.emprestimo.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupone.boavizinhanca.emprestimo.entity.Item;
import com.startupone.boavizinhanca.emprestimo.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;
	
	
	public Item saveItem(Item item) {
		if(item != null) item.setDataPublicacao(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
		return repository.save(item);
	}
	
	public List<Item> getItems(){
		List<Item> items = repository.findAll();
		pesquisarNotaProprietario(items);
		calcularPeriodoPublicacao(items);
		return items;
	}

	private void pesquisarNotaProprietario(List<Item> items) {
		if(items != null) {
			for(Item item : items) {
				// TODO colocar consulta Nota Usuário
				item.setNotaProprietario("5.0");
			}
		}
		
	}

	private void calcularPeriodoPublicacao(List<Item> items) {
		if(items != null) {
			LocalDate dateHoje = LocalDate.now();
			for(Item item : items) {
				if(item.getDataPublicacao() != null && (item.getDataPublicacao().length() == 10)) {				
					String dataPublicacao = item.getDataPublicacao();
					String ano = dataPublicacao.substring(0, 4);
					String mes = dataPublicacao.substring(5, 7);
					String dia = dataPublicacao.substring(8);
					LocalDate datePublicacao = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
					Period periodo = Period.between(datePublicacao, dateHoje);
					item.setDataPublicacao(periodo.getDays() == 0 ? "Hoje" : ("Há " + periodo.getDays() + (periodo.getDays() == 1 ? " dia" : " dias")));
				}
			}
		}
	}
	
	public Item getItemById(int id){
		Item item = repository.findById(id).orElse(null);
		if (item != null) { calcularPeriodoPublicacao(Arrays.asList(item)); pesquisarNotaProprietario(Arrays.asList(item));}
		else { item = new Item(); item.setObservacao("Item nexistente!!!"); }
		return item;
	}
	
	public List<Item> getItemsByIdProprietario(int id){
		List<Item> items = repository.findByIdUserProprietario(id);
		calcularPeriodoPublicacao(items);
		pesquisarNotaProprietario(items);
		return items;
	}
	
	public List<Item> getItemsBySearchText(String textSearch){
		List<Item> resultItems = new ArrayList<Item>();
		
		Map<Integer, Item> mapItems = new HashMap<Integer, Item>();
		String [] words = textSearch.split(" ");
		for(String word : Arrays.asList(words)) {			
			filtraItems(mapItems ,repository.findByNomeContains(word));
			filtraItems(mapItems ,repository.findByTagsContains(word));
		}
		filtraItems(mapItems ,repository.findByDescricaoContains(textSearch));
		filtraItems(mapItems ,repository.findByNomeLike(textSearch));
		filtraItems(mapItems ,repository.findByTagsLike(textSearch));
		filtraItems(mapItems ,repository.findByDescricaoLike(textSearch));
		
		resultItems.addAll(mapItems.values());
		
		calcularPeriodoPublicacao(resultItems);
		pesquisarNotaProprietario(resultItems);
		return resultItems;
	}
	
	private void filtraItems(Map<Integer, Item> mapItems, List<Item> items){
		for(Item item : items) {
			if(!mapItems.containsKey(item.getIdItem())) {
				mapItems.put(item.getIdItem(), item);
			}
		}
	}
	
	public String deleteItem(int id) {
		repository.deleteById(id);
		return "Item removido!"; 
	}
	
	public Item updateItem(Item item) {
		Item existingItem = repository.findById(item.getIdItem()).orElse(null);
		if(existingItem != null) {
			item.setDataPublicacao(existingItem.getDataPublicacao());
			item =  repository.save(item);
			calcularPeriodoPublicacao(Arrays.asList(item));
			pesquisarNotaProprietario(Arrays.asList(item));
			return item;
		}
		return null;
	}
}
