package com.rentalHive.rentalHive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping
    public String print(){
        return "hello boutaina" ;
    }
}
