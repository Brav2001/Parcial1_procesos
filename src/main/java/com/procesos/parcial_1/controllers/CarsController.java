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
    public List<CarsApi> saveCars() {
        String url="https://myfakeapi.com/api/cars/";
        CarsApi carsApi = restTemplate.getForObject(url, CarsApi.class);
        for (Cars cars : carsApi.getCars()) {

            Boolean res = carsServiceImp.saveCars(cars);
            Map response = new HashMap();
            if(res==true){
                System.out.println("registrado");;
            }else{
                response.put("status", "400");
                response.put("message", "No se guardo el usuario");
            }
        }
        return Arrays.asList(carsApi);
    }


}
