package com.example.recipes.service;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipe.utils.MyBeanUtils;
import com.example.recipes.dto.RecipesDTO;
import com.example.recipes.exceptions.RecipeExceptions;
import com.example.recipes.persistance.domain.Recipes;
import com.example.recipes.persistance.repo.RecipeReop;

@Service
public class RecipesService {

	private RecipeReop repo;
	private ModelMapper mapper;
	
	@Autowired
	public RecipesService(RecipeReop repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	private RecipesDTO mapToDTO(Recipes recipe) {
		return this.mapper.map(recipe, RecipesDTO.class);
	}

	private Recipes mapFromDTO(RecipesDTO recipe) {
		return this.mapper.map(recipe, Recipes.class);
	}

	public RecipesDTO createRecipe(RecipesDTO recipeDTO) {
		Recipes toSave = this.mapFromDTO(recipeDTO);
		Recipes saved = this.repo.save(toSave);
		return this.mapToDTO(saved);
	}

	public boolean deleteRecipe(Long id) {
		if (!this.repo.existsById(id)) {
			throw new RecipeExceptions();
		}
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public RecipesDTO findRecipeByID(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(RecipeExceptions::new));
	}

	public List<RecipesDTO> readRecipe() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public RecipesDTO updateRecipe(RecipesDTO recipe, Long id) {
		Recipes toUpdate = this.repo.findById(id).orElseThrow(RecipeExceptions::new);
		MyBeanUtils.mergeNotNull(recipe, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}

}
