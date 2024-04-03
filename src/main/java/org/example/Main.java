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
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {


        ClientDAO clientDAO = new ClientDAO();
        CarDAO carDAO = new CarDAO();
        RentDAO rentDAO = new RentDAO();
        ReviewDAO reviewDAO = new ReviewDAO();
        //Client client1 = new Client("Test1", "12345678999000",new HashSet<>());
        //Client client2 = new Client("Test2", "22345678999000",new HashSet<>());
        //clientDAO.createClient(client1);
        //clientDAO.createClient(client2);
        //System.out.println(client1.getId());
        //System.out.println(client2.getId());
        //Car car1 = new Car("12345687");
        //carDAO.createCar(car1);
        //Date startDate = new Date(2023,10,10);
        //Date endDate = new Date(2023,10,11);
        //Rent rent = new Rent(car1,client1,startDate,endDate,12.0,"Inactiv",10.0);
        //Rent rent2 = new Rent(car1,client1,startDate,endDate,13.0,"Inactiv",10.0);
        //Rent rent3 = new Rent(car1,client1,startDate,endDate,14.0,"Inactiv",10.0);
        //rentDAO.createRent(rent);
        //System.out.println(rent.getNrComanda());
        //rentDAO.createRent(rent2);
        //System.out.println(rent2.getNrComanda());
        //rentDAO.createRent(rent3);
        Menu.printWelcomeMenu();
        int option = pickOption();

        while (option!=0)
        {
            switch (option)
            {
                case 1:
                    clientDAO.createClient(Menu.registerAClient());
                    break;
                case 3:
                    carDAO.createCar(Menu.registerACar());
                    break;
                default:
                    break;

            }
            Menu.printMenu();
            option = pickOption();
        }


    }
    private static int pickOption()
    {
        boolean ok = true;
        int result = -1;
        Scanner scanner = new Scanner(System.in);
            try {
                result = scanner.nextInt();
                return result;
            }
            catch (InputMismatchException e)
            {
                return -1;
            }
    }
}