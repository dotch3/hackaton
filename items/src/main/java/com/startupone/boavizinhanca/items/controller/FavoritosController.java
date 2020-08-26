package com.startupone.boavizinhanca.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startupone.boavizinhanca.items.entity.Favorito;
import com.startupone.boavizinhanca.items.service.FavoritoService;

@RestController
@RequestMapping("/favoritos") 
public class FavoritosController {

	@Autowired
	private FavoritoService service;
	
	@GetMapping("/todos")
	public List<Favorito> findAllItems(){
		return service.getFavoritos();
	}
	
	@GetMapping("/porUsuario/{id}")
	public List<Favorito> findByIdUser(@PathVariable int id){
		return service.getFavoritosByUser(id);
	}
	
	@PostMapping("/favoritar")
	public Favorito favoritar(@RequestBody Favorito favorito) {
		return service.adicionar(favorito);
	}
	
	@DeleteMapping("/remover")
	public String removerFavorito(@RequestBody Favorito favorito) {
		return service.remover(favorito);
	}
}
