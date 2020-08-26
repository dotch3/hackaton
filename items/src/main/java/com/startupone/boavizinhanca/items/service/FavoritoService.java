package com.startupone.boavizinhanca.items.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupone.boavizinhanca.items.entity.Favorito;
import com.startupone.boavizinhanca.items.repository.FavoritoRepository;

@Service
public class FavoritoService {

	@Autowired
	private FavoritoRepository repository;
	
	public List<Favorito> getFavoritos(){
		List<Favorito> favoritos = repository.findAll();
		return favoritos;
	}
	
	public List<Favorito> getFavoritosByUser(Integer idUser){
		return repository.findByIdUser(idUser);
	}
	
	public Favorito adicionar(Favorito favorito) {
		favorito.setDataInteresse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
		return repository.save(favorito);
	}
	
	public String remover(Favorito favorito) {
		repository.delete(favorito);
		return "Favorito removido!!!";
	}
}
