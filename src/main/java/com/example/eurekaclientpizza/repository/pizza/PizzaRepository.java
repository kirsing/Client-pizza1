package com.example.eurekaclientpizza.repository.pizza;

import com.example.eurekaclientpizza.model.pizza.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
