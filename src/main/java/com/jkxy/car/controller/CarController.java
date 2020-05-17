package com.jkxy.car.controller;

import com.jkxy.car.pojo.Car;
import com.jkxy.car.service.CarService;
import com.jkxy.car.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }


    /**
     * 买车
     *
     * @param car
     * @return
     */
    @PostMapping("buyCar")
    public JSONResult buyCar(Car car) {
        String errorMsg = carService.buyCar(car.getId(), car.getAmount());
        return JSONResult.ok(errorMsg);
    }

    /**
     * 通过id查询
     *
     * @param name
     * @param from
     * @param to
     * @return
     */
    @PostMapping("findByNameWithPage")
    public JSONResult findById (String name,int from, int to) {
        if(from <1 || to<from){
            return JSONResult.errorException("输入记录位置错误");
        }
        List<Car> cars  = carService.findByNameWithPage(name,from-1,to-from+1);
        return JSONResult.ok(cars);
    }
}
