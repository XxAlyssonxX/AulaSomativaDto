package com.auladto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auladto.dto.PetDTO;
import com.auladto.entities.Pet;
import com.auladto.service.PetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class PetController {

	private final PetService PetService;

	@Autowired
	public PetController(PetService PetService) {
		this.PetService = PetService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> buscaPetControlId(@PathVariable Long id) {
		Pet Pet = PetService.getPetDTOById(id);
		if (Pet != null) {
			return ResponseEntity.ok(Pet);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Pet>> buscaTodasLigacoesControl() {
		List<Pet> Pet = PetService.getAllPetDTOs();
		return ResponseEntity.ok(Pet);
	}

	@PostMapping
	public ResponseEntity<PetDTO> savePetControl(@RequestBody @Valid PetDTO PetDTO) {
		PetDTO savePet = PetService.savePetDTO(PetDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(savePet);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PetDTO> alteraPetControl(@PathVariable Long id, @RequestBody @Valid PetDTO PetDTO) {
		PetDTO alteraPet = PetService.changePetDTO(id, PetDTO);

		if (alteraPet != null) {
			return ResponseEntity.ok(PetDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePetControl(@PathVariable Long id) {
		boolean delete = PetService.deletePetDTO(id);
		if (delete) {
			return ResponseEntity.ok().body("O pet foi excluido com o sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
