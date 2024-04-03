package org.example;

import org.example.cars.Car;
import org.example.cars.CarDAO;
import org.example.clients.Client;
import org.example.rents.Rent;
import org.example.rents.RentDAO;
import org.example.reviews.Review;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

    }

    public static void printWelcomeMenu()
    {
        System.out.println("Welcome to the rent a car app!");
        printMenu();
    }
    public static void printMenu()
    {
        System.out.println("Please chose one of the following options:");
        System.out.println("0 - End session");
        System.out.println("1 - Register a client");
        System.out.println("2 - Display all clients");
        System.out.println("3 - Register a car");
        System.out.println("4 - Display all cars");
        System.out.println("5 - Register a review");
        System.out.println("6 - Display all reviews");
        System.out.println("7 - Add a rent");
    }

    //Option 1
    public static Client registerClient()
    {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        System.out.print("Name: ");
        client.setName(scanner.next());
        System.out.print("CNP: ");
        client.setCnp(scanner.next());
        return client;
    }
    //Option 3
    public static Car registerCar()
    {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        System.out.print("VIN: ");
        car.setVin(scanner.next());
        System.out.print("Marca: ");
        car.setMarca(scanner.next());
        System.out.print("Model: ");
        car.setModel(scanner.next());
        System.out.print("Pret/zi: ");
        car.setPretZi(Double.valueOf(scanner.nextDouble()));
        return car;
    }
    public static void displayAllCars(CarDAO carDAO)
    {
        System.out.println(carDAO.findAllCars().toString());

    }
    //Option 5
    public static Review registerReview()
    {
        RentDAO rentDAO = new RentDAO();
        Scanner scanner = new Scanner(System.in);
        Long nrComanda;

        System.out.print("Introduceti numarul de comanda: ");
        nrComanda = scanner.nextLong();
        scanner.nextLine();
        System.out.println(nrComanda);
        Rent rentToSearch = rentDAO.getRentByNrComanda(nrComanda);
        Review review = new Review(rentToSearch);
        rentToSearch.setReview(review);
        if(rentToSearch == null)
        {
            System.out.print("Nu exista aceasta comanda");
        }
        else{
            System.out.print("Inserati textul acestui review: ");
            String test = scanner.nextLine();
            System.out.println(test);
            review.setText(test);
            System.out.print("Nota acordata: ");
            review.setNota(scanner.nextInt());
        }
        return review;
    }
}
