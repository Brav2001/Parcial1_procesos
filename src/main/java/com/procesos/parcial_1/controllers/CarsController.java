package com.procesos.parcial_1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.procesos.parcial_1.models.Cars;
import com.procesos.parcial_1.models.CarsApi;
import com.procesos.parcial_1.repository.CarsRepository;
import com.procesos.parcial_1.services.CarsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

import static org.springframework.core.convert.TypeDescriptor.forObject;

@RestController

public class CarsController {
    private final RestTemplate restTemplate;
    @Autowired
    public CarsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    CarsServiceImp carsServiceImp;
    @GetMapping("/saveCars")
    public ResponseEntity saveCars() {
        boolean res= carsServiceImp.saveCars();
        Map response = new HashMap();
        if(res==true){
            response.put("status", "201");
            response.put("message", "Se registraron todos los carros");
            return new ResponseEntity(response,HttpStatus.CREATED);
        }else{
            response.put("status", "500");
            response.put("message", "No se registraron los carro");
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/updateCar/{id}")
    public ResponseEntity updateCar(@PathVariable Long id,@RequestBody Cars cars){
        Boolean res = carsServiceImp.updateCars(id,cars);
        Map response = new HashMap();
        if(res==true){
            response.put("status", "200");
            response.put("message", "Se actualizó el carro");
            return new ResponseEntity(response, HttpStatus.OK) ;
        }else{
            response.put("status", "400");
            response.put("message", "No se actualizó el carro");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

    }


}
