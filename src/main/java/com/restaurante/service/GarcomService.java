package com.restaurante.service;

import java.util.List;
import java.util.Optional;

import com.restaurante.model.Garcom;

public interface GarcomService {

	List<Garcom> findAll();
	Optional<Garcom> findOne(Integer id);
	Garcom create(Garcom garcom);
	Garcom update(Garcom garcom);
	void delete(Integer id);
	
}
