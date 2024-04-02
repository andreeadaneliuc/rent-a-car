package org.example;

import org.example.cars.CarDAO;
import org.example.clients.Client;
import org.example.clients.ClientDAO;
import org.example.rents.RentDAO;
import org.example.reviews.ReviewDAO;

public class Main {
    public static void main(String[] args) {

        ClientDAO clientDAO = new ClientDAO();
        CarDAO carDAO = new CarDAO();
        RentDAO rentDAO = new RentDAO();
        ReviewDAO reviewDAO = new ReviewDAO();

        Client client1 = new Client("Test 1", "123456789990");

    }
}