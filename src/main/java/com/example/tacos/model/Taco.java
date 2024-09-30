package com.example.tacos.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createdAt = new Date();
@NotNull
@Size(min = 5,message = "Name must be at least 5 characters long")
    private String name;
@NotNull
        @Size(min = 1,message = "you should select at least 1 ingredient")
    List<Ingredient> ingredients;
}
