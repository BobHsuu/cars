package com.jkxy.car.service.Impl;

import com.jkxy.car.dao.CarDao;
import com.jkxy.car.dao.CarSellDao;
import com.jkxy.car.pojo.Car;
import com.jkxy.car.pojo.CarSell;
import com.jkxy.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Resource
    private CarDao carDao;

    @Resource
    private CarSellDao carSellDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(int id,Car car) {
        car.setId(id);
        carDao.updateById(id,car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String buyCar(int id, int amount) {
        if(amount<1){
            return "购买数量异常";
        }
        Car car = carDao.findById(id);
        int stock  = car.getStock();
        if(amount>stock){
            return "库存不足";
        }
        car.setStock(stock-amount);
        carDao.updateById(id,car);

        //insert sell log
        CarSell carSell = new CarSell();
        carSell.setCarId(car.getId());
        carSell.setAmount(amount);
        carSell.setTradeDate( new Timestamp(System.currentTimeMillis()));

        carSellDao.insertCarSell(carSell);
        return null;
    }

    @Override
    public List<Car> findByNameWithPage(String name,int from,int pageSize) {
        return carDao.findByNameWithPage(name,from,pageSize);
    }
}
