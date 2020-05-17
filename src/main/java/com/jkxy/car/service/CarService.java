package com.jkxy.car.service;

import com.jkxy.car.pojo.Car;

import java.util.List;


public interface CarService {

    List<Car> findAll();

    Car findById(int id);

    List<Car> findByCarName(String carName);

    void deleteById(int id);

    void updateById(int id,Car car);

    void insertCar(Car car);

    List<Car> findByNameWithPage(String name,int from,int pageSize);

    void validateCarAndUpate(int id, int amount);
}
