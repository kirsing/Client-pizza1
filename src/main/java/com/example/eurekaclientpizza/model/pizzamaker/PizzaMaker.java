package com.example.eurekaclientpizza.model.pizzamaker;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class PizzaMaker {
        @Id
    @GeneratedValue
    private int id;

        private String firstName;

        private String lastName;

        private String position;

        private int salary;
}
