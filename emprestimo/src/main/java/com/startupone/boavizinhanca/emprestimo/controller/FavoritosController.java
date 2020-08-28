package com.startupone.boavizinhanca.emprestimo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startupone.boavizinhanca.emprestimo.entity.Favorito;
import com.startupone.boavizinhanca.emprestimo.service.FavoritoService;

@CrossOrigin(maxAge = 3600) 
@RestController
@RequestMapping("/favoritos") 
public class FavoritosController {

	@Autowired
	private FavoritoService service;
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@GetMapping("/todos")
	public List<Favorito> findAllItems(){
		return service.getFavoritos();
	}
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@GetMapping("/porUsuario/{id}")
	public List<Favorito> findByIdUser(@PathVariable int id){
		return service.getFavoritosByUser(id);
	}
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@PostMapping("/favoritar")
	public Favorito favoritar(@RequestBody Favorito favorito) {
		return service.adicionar(favorito);
	}
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@DeleteMapping("/remover")
	public String removerFavorito(@RequestBody Favorito favorito) {
		return service.remover(favorito);
	}
}
