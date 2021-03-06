package com.jkxy.car.dao;

import com.jkxy.car.pojo.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Mapper
public interface CarDao {
    @Select("select * from carMessage")
    List<Car> findAll();

    @Select("select * from carMessage where id = #{id}  for update")
    Car findById(int id);

    @Select("select * from carMessage where carName = #{carName}")
    List<Car> findByCarName(String carName);

    @Delete("delete from carMessage where id = #{id}")
    void deleteById(int id);

    @Update("update carMessage set stock=#{stock},carName=#{carName},carType=#{carType},price=#{price},carSeries=#{carSeries} where id = #{id}")
    boolean updateById(Car car);

    @Insert("insert into carMessage(carName,carType,price,carSeries) values(#{carName},#{carType},#{price},#{carSeries})")
    void insertCar(Car car);

    @Select("select * from carMessage where carName like '%${name}%' limit #{from},#{pageSize}")
    List<Car> findByNameWithPage(String name,int from,int pageSize);
}
