package com.example.recipes.persistance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Recipes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "recipe_name", unique = true)
	@NotNull
	@Size(min = 0, max = 55)
	private String name;

	@NotNull
	private String flavour;
	@NotNull
	private String type;
	
	public Recipes() { // REQUIRED
		super();
	}

	public Recipes(String name, String flavour, String type){
		super();
		this.name = name;
		this.flavour = flavour;
		this.type = type;

	}

	public void setId(long l) {
		// TODO Auto-generated method stub
		
	}


}



