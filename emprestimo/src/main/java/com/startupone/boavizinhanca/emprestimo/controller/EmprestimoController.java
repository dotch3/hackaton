package com.startupone.boavizinhanca.emprestimo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startupone.boavizinhanca.emprestimo.entity.Emprestimo;
import com.startupone.boavizinhanca.emprestimo.service.EmprestimoService;

@CrossOrigin(maxAge = 3600) 
@RestController
@RequestMapping("/emprestimos") 
public class EmprestimoController {
	
	@Autowired
	private EmprestimoService service;
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@GetMapping("/porUsuario/{id}")
	public List<Emprestimo> findByIdUser(@PathVariable int id){
		return service.getEmprestimosByUser(id);
	}
	
	@CrossOrigin(origins = {"http://54.163.66.128:8080","http://localhost:8080","http://localhost:8888","http://204.236.210.118"},maxAge=4800, allowCredentials="false")
	@PostMapping("/enviarProposta")
	public Emprestimo enviarProposta(@RequestBody Emprestimo proposta) {
		return service.registrarProposta(proposta);
	}
	

}
