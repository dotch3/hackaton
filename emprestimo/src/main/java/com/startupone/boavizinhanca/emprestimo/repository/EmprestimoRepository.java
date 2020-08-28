package com.startupone.boavizinhanca.emprestimo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startupone.boavizinhanca.emprestimo.entity.Emprestimo;
import com.startupone.boavizinhanca.emprestimo.entity.EmprestimoId;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoId>{

	List<Emprestimo> findByIdUser(Integer id);
}
