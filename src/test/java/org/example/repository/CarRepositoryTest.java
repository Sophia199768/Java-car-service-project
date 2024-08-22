package org.example.repository;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.example.core.model.car.Car;
import org.example.service.Exception.Exceptions;
import org.example.service.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class CarRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    private CarRepository carRepository;

    @BeforeAll
    static void init() throws Exception {
        Class.forName("org.postgresql.Driver");
    }

    @BeforeEach
    void setUp() throws Exception {
        Connection connection = DriverManager.getConnection(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        );

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.yml", new ClassLoaderResourceAccessor(), database);

        liquibase.update();

        carRepository = new CarRepository(connection);
    }

    @Test
    @DisplayName("create should add a new car to the repository")
    void create_shouldAddNewCarToRepository() throws Exceptions {
        Car car = new Car();
        car.setId(1);
        car.setCarBrand("Toyota");
        car.setCarModel("Camry");
        car.setPrice(30000L);
        car.setCondition("New");

        car = carRepository.create(car);


        List<Car> cars = carRepository.read();
        assertEquals(2, cars.size());
        assertTrue(cars.contains(car));
    }

    @Test
    @DisplayName("update should modify an existing car in the repository")
    void update_shouldModifyExistingCarInRepository() throws Exceptions {
        Car car = new Car();
        car.setId(1);
        car.setCarBrand("Toyota");
        car.setCarModel("Camry");
        car.setPrice(30000L);
        car.setCondition("New");

        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setId(1);
        updatedCar.setCarBrand("Toyota");
        updatedCar.setCarModel("Camry");
        updatedCar.setPrice(28000L);
        updatedCar.setCondition("Used");

        carRepository.update(updatedCar);

        List<Car> cars = carRepository.read();
        assertEquals(2, cars.size());
        assertEquals(updatedCar, cars.get(1));
    }

    @Test
    @DisplayName("read should return all cars in the repository")
    void read_shouldReturnAllCarsInRepository() throws Exceptions {
        Car car1 = new Car();
        car1.setId(1);
        car1.setCarBrand("Toyota");
        car1.setCarModel("Camry");
        car1.setPrice(30000L);
        car1.setCondition("New");

        Car car2 = new Car();
        car2.setId(1);
        car2.setCarBrand("Honda");
        car2.setCarModel("Accord");
        car2.setPrice(25000L);
        car2.setCondition("Used");

        car1 = carRepository.create(car1);
        car2 = carRepository.create(car2);

        List<Car> cars = carRepository.read();
        assertEquals(3, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    @DisplayName("delete should remove a car from the repository")
    void delete_shouldRemoveCarFromRepository() throws Exceptions {
        Car car = new Car();
        car.setId(1);
        car.setCarBrand("Toyota");
        car.setCarModel("Camry");
        car.setPrice(30000L);
        car.setCondition("New");

        car = carRepository.create(car);

        carRepository.delete(car);

        List<Car> cars = carRepository.read();
        assertEquals(1, cars.size());
    }
}
