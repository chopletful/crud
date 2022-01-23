package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

	@GetMapping(value = "/cars")
	public String printWelcome(ModelMap model, @RequestParam(name = "count", defaultValue = "5") int count) {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("VAZ","RED","OKA"));
		cars.add(new Car("VAZ","BLUE","SHESTERKA"));
		cars.add(new Car("VAZ","RED","VNEDOROZHNIK"));
		cars.add(new Car("VAZ","GREEN","SHESTERKA"));
		cars.add(new Car("VAZ","WHITE","SEMEROCHKA"));
		CarService editedCars = new CarServiceImpl();
		cars = editedCars.count(cars,count);
		model.addAttribute("cars", cars);
		return  "car";

	}


	
}