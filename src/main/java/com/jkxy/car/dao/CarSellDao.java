package com.jkxy.car.dao;

import com.jkxy.car.pojo.CarSell;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CarSellDao {

    @Insert("insert into carsell (carId,amount,tradeDate) values (#{carId},#{amount},#{tradeDate})")
    void insertCarSell(CarSell carSell);
}
