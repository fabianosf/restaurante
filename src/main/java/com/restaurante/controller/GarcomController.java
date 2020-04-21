package com.restaurante.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurante.exception.ResourceNotFoundException;
import com.restaurante.model.Garcom;
import com.restaurante.service.GarcomService;

@RestController
@RequestMapping("/api/garcom")
public class GarcomController {

	@Autowired
	private static GarcomService garcomService;

	@GetMapping("/garcons")
	public List<Garcom> findAllGarcom() {
		return garcomService.findAll();
	}

	@GetMapping("/garcons/{id}")
	public ResponseEntity<Garcom> findOne(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Garcom garcom = garcomService.findOne(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		return ResponseEntity.ok().body(garcom);
	}

	@PostMapping("/garcons")
	public Garcom create(@Valid @RequestBody Garcom garcom) {
		return garcomService.create(garcom);
	}

	@PutMapping("/garcons/{id}")
	public ResponseEntity<Garcom> update(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody Garcom garcomDetails) throws ResourceNotFoundException {
		Garcom garcom = garcomService.findOne(id)
				.orElseThrow(() -> new ResourceNotFoundException("Garcom nao encontrado com id" + id));
		garcom.setId(garcomDetails.getId());
		garcom.setNome(garcomDetails.getNome());
		garcom.setUsuario(garcomDetails.getUsuario());
		garcom.setSenha(garcomDetails.getSenha());
		final Garcom updateGarcom = garcomService.create(garcom);
		return ResponseEntity.ok(updateGarcom);
	}

	@DeleteMapping("/garcons/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Garcom garcom = garcomService.findOne(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

		garcomService.delete(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
