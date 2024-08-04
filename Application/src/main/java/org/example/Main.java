package org.example;

import org.example.controller.CarController;
import org.example.controller.OrderController;
import org.example.controller.UserController;
import org.example.mapper.OrderMapper;
import org.example.mapper.UserMapper;
import org.example.parser.CommandParser;
import org.example.parser.command.Commandable;
import org.example.repository.CarRepository;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.example.service.CarService;
import org.example.service.OrderService;
import org.example.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CarRepository repository = new CarRepository();
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();

        CarService service = new CarService(repository);
        UserService userService = new UserService(userRepository, new UserMapper());
        OrderService orderService = new OrderService(orderRepository, new OrderMapper());

        CarController carController = new CarController(service);
        OrderController orderController = new OrderController(orderService);
        UserController userController = new UserController(userService);

        ApplicationState state = new ApplicationState();

        CommandParser parser = new CommandParser(carController, orderController, userController);

        System.out.println("register or login");
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("exit")) break;

            Commandable comm = parser.parse(command, state);

            if (comm == null) continue;

            try {
                comm.execute(state);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}