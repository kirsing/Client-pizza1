package com.example.eurekaclientpizza.controller;

import com.example.eurekaclientpizza.model.Pizza;
import com.example.eurekaclientpizza.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class PizzaController {
    PizzaRepository pizzaRepository;

    @GetMapping("/pizza")
    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }   // new ParameterizedTypeReference<List<Rate>>  в новом проекте с рест темплейтом + круды + обращаться на пицца сервер
    @PostMapping("/pizza")
    public Pizza createPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @PutMapping("/pizza/{id}")
    public Pizza updatePizza(@PathVariable(value = "id") int id,  @RequestBody Pizza pizza) {
        Pizza pizzaNew = pizzaRepository.findById(id).get();
        pizzaNew.setName(pizza.getName());
        pizzaNew.setDiameter(pizza.getDiameter());
        pizzaNew.setWeight(pizza.getWeight());
        return pizzaRepository.save(pizzaNew);
    }

    @DeleteMapping("/pizza/{id}")
    public void deleteById(@PathVariable int id) {
        pizzaRepository.deleteById(id);
    }

}
