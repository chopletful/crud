package web.service;

import web.model.Car;

import java.util.List;

public class CarServiceImpl implements CarService{
    @Override
    public  List<Car> count(List<Car> cars, int n) {
         return n > cars.size() || n>=5 ?  cars:  cars.subList(0,n);
    }
}
