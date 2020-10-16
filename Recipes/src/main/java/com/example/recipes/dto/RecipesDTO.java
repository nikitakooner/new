package com.example.recipes.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RecipesDTO {

	private Long id;

	private String name;

	private String type;

	private String flavour;

	public RecipesDTO(Long id, String name, String type, String flavour) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.flavour = flavour;
	}

}