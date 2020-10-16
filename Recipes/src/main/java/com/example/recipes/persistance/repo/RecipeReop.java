package com.example.recipes.persistance.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.recipes.persistance.domain.Recipes;

// JPA -> Java Persistence API

@Repository
public interface RecipeReop extends JpaRepository <Recipes, Long> {

	List<Recipes> findByType(String type);
	}

