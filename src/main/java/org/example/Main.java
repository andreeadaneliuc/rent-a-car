package org.example;

import org.example.cars.CarDAO;
import org.example.clients.ClientDAO;
import org.example.rents.RentDAO;
import org.example.reviews.ReviewDAO;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static ClientDAO clientDAO = new ClientDAO();
    private static CarDAO carDAO = new CarDAO();
    private static RentDAO rentDAO = new RentDAO();
    private static ReviewDAO reviewDAO = new ReviewDAO();

    public static void main(String[] args) throws SQLException {

        /*
        Client client1 = new Client("Test1", "12345678999000",new HashSet<>());
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
        rentDAO.createRent(rent3);

         */
        Menu.printWelcomeMessage();
        /*
        LocalDate startDate = LocalDate.of(2023, 05, 10);
        LocalDate endDate = LocalDate.of(2023, 11, 10);
        Client client1 = new Client("Test1", "12345678999000", new HashSet<>());
        clientDAO.createClient(client1);
        Car car1 = new Car("123456878", "marca", "model", "motorizare", 1990, "rosu", 10.0, true);
        Car car2 = new Car("9876545312", "marca2", "model2", "motorizare2", 2024, "albastru", 11.0, true);
        carDAO.createCar(car1);
        carDAO.createCar(car2);
        Rent rent3 = new Rent(car1, client1, startDate, endDate, "Inactiv", 10.0);
        rentDAO.createRent(rent3);
         */

        Menu.printUserTypeSelectionMenu();

        int option = pickOption();
        while (option != 0) {
            switch (option) {
                case 1 -> printAdministratorMenu();
                case 2 -> printClientMenu();
            }

            Menu.printUserTypeSelectionMenu();
            option = pickOption();
        }
    }

    private static int pickOption() {
        boolean ok = true;
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        try {
            result = scanner.nextInt();
            return result;
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    private static void printAdministratorMenu() {
        Menu.printAdministratorMenu();
        int option = pickOption();
        while (option != 0) {
            switch (option) {
                case 1:
                    clientDAO.createClient(Menu.registerClient());
                    break;
                case 2:
                    Menu.displayAllClients(clientDAO);
                    break;
                case 3:
                    carDAO.createCar(Menu.registerCar());
                    break;
                case 4:
                    Menu.displayAllCars(carDAO);
                    break;
                case 5:
                    reviewDAO.createReview(Menu.registerReview());
                    break;
                case 6:
                    Menu.displayAllReviews(reviewDAO);
                    break;
                case 7:
                    System.out.println(carDAO.findAllAvailableCars());
                    break;
                case 8:
                    rentDAO.createRent(Menu.registerRent(carDAO, clientDAO));
                    break;
                default:
                    break;

            }
            Menu.printAdministratorMenu();
            option = pickOption();
        }
    }

    private static void printClientMenu() {
        Menu.printClientMenu();
        int option = pickOption();
        while (option != 0) {
            switch (option) {
                case 1 -> clientDAO.createClient(Menu.registerClient());
                case 2 -> carDAO.createCar(Menu.registerCar());
                case 3 -> Menu.displayAllCars(carDAO);
                case 4 -> reviewDAO.createReview(Menu.registerReview());
                case 5 -> Menu.displayAllReviews(reviewDAO);
                case 6 -> System.out.println(carDAO.findAllAvailableCars());
                case 7 -> rentDAO.createRent(Menu.registerRent(carDAO, clientDAO));
            }
            Menu.printClientMenu();
            option = pickOption();
        }
    }
}