package com.procesos.parcial_1.services;

import com.procesos.parcial_1.models.Cars;
import com.procesos.parcial_1.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsServiceImp implements CarsService{
    @Autowired
    private CarsRepository carsRepository;
    @Override
    public Boolean saveCars(Cars cars) {
        try{
            carsRepository.save(cars);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Cars getCars(Long id){

        return carsRepository.findById(id).get();
    }

    @Override
    public List<Cars> allCars() {
        return carsRepository.findAll();
    }

}
