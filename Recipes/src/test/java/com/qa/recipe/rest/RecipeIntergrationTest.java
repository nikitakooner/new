package com.qa.recipe.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.recipes.persistance.domain.Recipes;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:recipe-schema.sql",
		"classpath:recipe-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class RecipeIntergrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Recipes newRecipe = new Recipes("lemon drizzle", "cake" ,"lemon");
		String requestBody = this.mapper.writeValueAsString(newRecipe);
		RequestBuilder request = post("/create").contentType(MediaType.APPLICATION_JSON).content(requestBody);

		ResultMatcher checkStatus = status().is(201);

		Recipes savedRecipe = new Recipes("lemon drizzle", "cake" ,"lemon");
		savedRecipe.setId(2L); 

		String resultBody = this.mapper.writeValueAsString(savedRecipe);
		ResultMatcher checkBody = content().json(resultBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);

		MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();

		String reqBody = result.getResponse().getContentAsString();

		Recipes recipeResult = this.mapper.readValue(reqBody, Recipes.class);
	}

	@Test
	void testUpdate() throws Exception {
		Recipes newRecipe = new Recipes("lemon drizzle", "cake" ,"lemon");
		String requestBody = this.mapper.writeValueAsString(newRecipe);
		RequestBuilder request = put("/update?id=1").contentType(MediaType.APPLICATION_JSON).content(requestBody);

		ResultMatcher checkStatus = status().isAccepted();

		Recipes savedRecipe = new Recipes("lemon drizzle", "cake" ,"lemon");
		savedRecipe.setId(1L);

		String resultBody = this.mapper.writeValueAsString(savedRecipe);
		ResultMatcher checkBody = content().json(resultBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/remove/1");

		ResultMatcher checkStatus = status().is(200);

		this.mockMVC.perform(request).andExpect(checkStatus);

	}

	@Test
	void testRead() throws Exception {
		Recipes recipe = new Recipes("cake", "cake" ,"cake");
		recipe.setId(1L); // 
		List<Recipes> recipes = new ArrayList<>();
		recipes.add(recipe);
		String responseBody = this.mapper.writeValueAsString(recipes);

		this.mockMVC.perform(get("/get")).andExpect(status().isOk()).andExpect(content().json(responseBody));
	}

}
