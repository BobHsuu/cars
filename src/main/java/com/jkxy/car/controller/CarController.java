package com.jkxy.car.controller;

import com.jkxy.car.FO.CarFO;
import com.jkxy.car.pojo.Car;
import com.jkxy.car.service.CarService;
import com.jkxy.car.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 批量查询
     *
     * @return
     */
    @GetMapping("cars")
    public JSONResult findList(CarFO carFO) {
        List<Car> cars;
        String carName = carFO.getCarName();
        Integer from = carFO.getFrom();
        Integer to = carFO.getTo();

        if (carFO.getCarName()==null){
            cars = carService.findAll();
        }
        else if (carFO.getFrom()==null && carFO.getTo()==null){
            cars = carService.findByCarName(carName);
        }else {
            cars  = carService.findByNameWithPage(carName,from-1,to-from+1);

        }
        return JSONResult.ok(cars);
    }


    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("car/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }



    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("car/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PutMapping("car/{id}")
    public JSONResult updateById(@PathVariable int id,Car carFo) {
        try{
            carService.updateById(id, carFo);
            return JSONResult.ok();
        }
        catch (Exception e){
            return  JSONResult.errorException(e.getMessage());
        }

    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PatchMapping ("car/{id}")
    public JSONResult patchById(@PathVariable int id,CarFO carFo){
        try{
            carService.validateCarAndUpate(id, carFo.getAmount()) ;
            return JSONResult.ok();
        }
        catch (Exception e){
            return  JSONResult.errorException(e.getMessage());
        }

    }


    /**
     * 增加
     *
     * @param car
     * @return
     */
    @PostMapping("car")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }






}
