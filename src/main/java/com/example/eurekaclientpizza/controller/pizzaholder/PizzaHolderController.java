package com.example.eurekaclientpizza.controller.pizzaholder;

import com.example.eurekaclientpizza.model.pizza.Pizza;
import com.example.eurekaclientpizza.model.pizzaholder.PizzaHolder;
import com.example.eurekaclientpizza.repository.pizzaholder.PizzaHolderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/custom")
public class PizzaHolderController {
    PizzaHolderRepository pizzaHolderRepository;

    @GetMapping("/holder")
    public List<PizzaHolder> getAllPizzaHolders() {
        log.debug("/holder");
        return pizzaHolderRepository.findAll();
    }
    @PostMapping("/holder")
    public PizzaHolder createPizzaHolder(@RequestBody PizzaHolder pizzaHolder) {
        return pizzaHolderRepository.save(pizzaHolder);
    }

//    @PutMapping("/holder/{id}")
//    public PizzaHolder updatePizzaHolder(@PathVariable(value = "id") int id,  @RequestBody PizzaHolder pizzaHolder) {
//        PizzaHolder pizzaHolderNew = pizzaHolderRepository.findById(id).get();
//        pizzaHolderNew.setN(pizzaHolder.getName());
//        pizzaNew.setDiameter(pizza.getDiameter());
//        pizzaNew.setWeight(pizza.getWeight());
//        return pizzaRepository.save(pizzaNew);
//    }

    @DeleteMapping("/holder/{id}")
    public void deleteById(@PathVariable int id) {
        pizzaHolderRepository.deleteById(id);
    }
}
