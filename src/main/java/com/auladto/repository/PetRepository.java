package com.auladto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auladto.entities.Pet;

public interface  PetRepository extends JpaRepository<Pet,Long>{

	

}