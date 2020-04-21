package com.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurante.model.Garcom;

public interface GarcomRepository extends JpaRepository<Garcom, Integer> {
	
	List<Garcom> buscaNome(String nome);

}
