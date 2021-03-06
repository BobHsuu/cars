package com.jkxy.car.service.Impl;

import com.jkxy.car.dao.CarDao;
import com.jkxy.car.pojo.Car;
import com.jkxy.car.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Resource
    private CarDao carDao;



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
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }



    @Override
    public List<Car> findByNameWithPage(String name,int from,int pageSize) {
        return carDao.findByNameWithPage(name,from,pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void validateCarAndUpate(int id, int amount)  throws Exception{
        try{
            if(amount<1){
                throw new Exception("购买数量异常");
            }
            Car car = carDao.findById(id);
            int stock  = car.getStock();
            if(amount>stock){
                throw new Exception("库存不足");
            }
            if (amount!=0){
                car.setStock(stock-amount);
            }
            updateById(id,car);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw  e;
        }
    }
}
