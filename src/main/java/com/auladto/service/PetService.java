package com.auladto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auladto.dto.PetDTO;
import com.auladto.entities.Pet;
import com.auladto.repository.PetRepository;

@Service
public class PetService {
	private final PetRepository PetRepository;
	

	@Autowired
	public PetService(PetRepository PetRepository) {
		this.PetRepository = PetRepository;
	}

	public List<Pet> getAllPetDTOs() {
		return PetRepository.findAll();
	}

	public Pet getPetDTOById(Long id) {
		Optional<Pet> Pet = PetRepository.findById(id);
		return Pet.orElse(null);
	}

	public PetDTO savePetDTO(PetDTO PetDTO) {
		Pet Pet = new Pet(PetDTO.nome(), PetDTO.documento(),PetDTO.nascimento());
		Pet salvarPet = PetRepository.save(Pet);
		return new PetDTO(salvarPet.getId(), salvarPet.getNome(), salvarPet.getDocumento(),salvarPet.getNascimento());
	}

	public PetDTO changePetDTO(Long id, PetDTO PetDTO) {
		Pet existePet = PetRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
		
		existePet.setNome(PetDTO.nome());
		existePet.setDocumento(PetDTO.documento());
		existePet.setNascimento(PetDTO.nascimento());
		
		Pet updatePet = PetRepository.save(existePet);
		return new PetDTO(updatePet.getId(), updatePet.getNome(), updatePet.getDocumento(), updatePet.getNascimento());
	}

	public boolean deletePetDTO(Long id) {
		Optional<Pet> existePetDTO= PetRepository.findById(id);
		if (existePetDTO.isPresent()) {
			PetRepository.deleteById(id);
			return true;
		}
		return false;
	}
}