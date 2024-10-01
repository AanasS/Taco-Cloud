package com.example.tacos.controllers;

import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Ingredient.Type;
import com.example.tacos.model.Taco;
import com.example.tacos.model.TacoOrder;
import com.example.tacos.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {


    private final IngredientRepository ingredientRepository;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
//    This method will also be invoked when a request is handled and
//will construct a list of Ingredient objects to be put into the model.
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),  filterByType((List<Ingredient>) ingredients, type));
        }
        log.info(model.toString());
    }
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

//    When showDesignForm() handles a GET request for /design, it doesn’t really do
//    much. The main thing it does is return a String value of "design", which is the logical
//    name of the view that will be used to render the model to the browser. But before
//    it does that, it also populates the given Model with an empty Taco object under a key
//    whose name is "design". This will enable the form to have a blank slate on which to
//    create a taco masterpiece.
    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
            return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder){
if(errors.hasErrors()){
    return "design";
}
            tacoOrder.addTaco(taco);
            log.info("Processing taco: {}",taco);
            return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients ,Type type){
        return ingredients
                .stream()
                .filter(i -> i.getType().equals(type))
                .collect(Collectors.toList());
    }
}

