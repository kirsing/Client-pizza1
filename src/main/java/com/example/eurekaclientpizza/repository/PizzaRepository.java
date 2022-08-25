package com.example.eurekaclientpizza.repository;

import com.example.eurekaclientpizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
