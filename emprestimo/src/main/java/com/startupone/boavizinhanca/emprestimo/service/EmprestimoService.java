package com.startupone.boavizinhanca.emprestimo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startupone.boavizinhanca.emprestimo.entity.Emprestimo;
import com.startupone.boavizinhanca.emprestimo.entity.Item;
import com.startupone.boavizinhanca.emprestimo.repository.EmprestimoRepository;
import com.startupone.boavizinhanca.emprestimo.repository.ItemRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository repository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Emprestimo registrarProposta(Emprestimo proposta){
		proposta.setStatus(0);
		proposta.setAvaliacao("0");
		Emprestimo empr = repository.save(proposta);
		Item item = itemRepository.findById(empr.getIdItem()).orElse(null);
		empr.setItem(item);
		return empr;
	}
	
	public List<Emprestimo> getEmprestimosByUser(Integer idUser){
		List<Emprestimo> emprestimos = repository.findByIdUser(idUser);
		for(Emprestimo empr : emprestimos) {
			Item item = itemRepository.findById(empr.getIdItem()).orElse(null);
			empr.setItem(item);
		}
		return emprestimos;
	}
}
