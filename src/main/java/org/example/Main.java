package org.example;

import org.example.cars.Car;
import org.example.cars.CarDAO;
import org.example.clients.Client;
import org.example.clients.ClientDAO;
import org.example.clients.ClientDAO;
import org.example.rents.Rent;
import org.example.rents.RentDAO;
import org.example.reviews.Review;
import org.example.reviews.ReviewDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws SQLException {


        ClientDAO clientDAO = new ClientDAO();
        CarDAO carDAO = new CarDAO();
        RentDAO rentDAO = new RentDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        Client client1 = new Client("Test1", "12345678999000",new HashSet<>());
        Client client2 = new Client("Test2", "22345678999000",new HashSet<>());
        System.out.println(client1.getId());
        System.out.println(client2.getId());
        clientDAO.createClient(client1);
        clientDAO.createClient(client2);
        Car car1 = new Car("12345");
        carDAO.createCar(car1);
        Date startDate = new Date(2023,10,10);
        Date endDate = new Date(2023,10,11);
        Rent rent = new Rent(car1,client1,startDate,endDate,10.0,"Inactiv",10.0);
        rentDAO.createRent(rent);

    }
}