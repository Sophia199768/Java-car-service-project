package org.example.application.factory;

import org.example.application.controller.CarController;
import org.example.application.controller.OrderController;
import org.example.application.controller.UserController;
import org.example.in.parser.CommandParser;
import org.example.in.parser.Parser;
import org.example.service.mapper.CarMapper;
import org.example.service.mapper.OrderMapper;
import org.example.service.mapper.UserMapper;
import org.example.service.repository.CarRepository;
import org.example.service.repository.OrderRepository;
import org.example.service.repository.UserRepository;
import org.example.service.service.CarService;
import org.example.service.service.OrderService;
import org.example.service.service.UserService;

public class ParserFactory {
    public static Parser createParser() {
        CarRepository carRepository = new CarRepository();
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();

        CarService carService = new CarService(carRepository, new CarMapper());
        UserService userService = new UserService(userRepository, new UserMapper());
        OrderService orderService = new OrderService(orderRepository, new OrderMapper());

        CarController carController = new CarController(carService);
        OrderController orderController = new OrderController(orderService);
        UserController userController = new UserController(userService);

        return new CommandParser(carController, orderController, userController);
    }
}
