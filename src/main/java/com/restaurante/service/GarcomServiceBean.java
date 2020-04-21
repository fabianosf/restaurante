package com.restaurante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurante.model.Garcom;
import com.restaurante.repository.GarcomRepository;

@Service
public class GarcomServiceBean implements GarcomService {

	@Autowired
	private GarcomRepository garcomRepository;

	@Override
	public List<Garcom> findAll() {
		List<Garcom> garcons = garcomRepository.findAll();
		return garcons;

	}

	@Override
	public Optional<Garcom> findOne(Integer id) {
		Optional<Garcom> garcom = garcomRepository.findById(id);
		return garcom;
	}

	@Override
	public Garcom create(Garcom garcom) {
		if(garcom.getId() != null) {
			return null;
		}
		Garcom saveGarcom = garcomRepository.save(garcom);
		return saveGarcom;
	}

	@Override
	public Garcom update(Garcom garcom) {
		 Optional<Garcom> garcomPersisted = findOne(garcom.getId());
		 if(garcomPersisted == null) {
			 return null;
		 }
		 Garcom updateGarcom = garcomRepository.save(garcom);
		return updateGarcom;
	}

	@Override
	public void delete(Integer id) {
		garcomRepository.deleteById(id);
	}

}
