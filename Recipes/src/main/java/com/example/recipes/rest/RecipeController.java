package com.example.recipes.rest;

import java.util.List;

import javax.websocket.server.PathParam;

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

import com.example.recipes.dto.RecipesDTO;
import com.example.recipes.service.RecipesService;
@RestController
@RequestMapping("/recipe")
public class RecipeController {

	private RecipesService service;

	@Autowired
	public RecipeController(RecipesService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createRecipe")
	public ResponseEntity<RecipesDTO> createRecipe(@RequestBody RecipesDTO recipe) {
		return new ResponseEntity<>(this.service.createRecipe(recipe), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteRecipe/{id}")
	public ResponseEntity<RecipesDTO> deleteRecipe(@PathVariable Long id) {
		return this.service.deleteRecipe(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<RecipesDTO> getRecipe(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findRecipeByID(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<RecipesDTO>> getAllRecipes() {
		return ResponseEntity.ok(this.service.readRecipe());
	}

	@PutMapping("/updateRecipe")
	public ResponseEntity<RecipesDTO> updateRecipe(@PathParam("id") Long id, @RequestBody RecipesDTO recipe) {{
		return new ResponseEntity<>(this.service.updateRecipe(recipe, id), HttpStatus.ACCEPTED);
	}
}
}
