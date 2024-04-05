package org.example;

import org.example.cars.Car;
import org.example.cars.CarDAO;
import org.example.clients.Client;
import org.example.clients.ClientDAO;
import org.example.rents.Rent;
import org.example.rents.RentDAO;
import org.example.reviews.Review;
import org.example.reviews.ReviewDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void printUserTypeSelectionMenu() {
        System.out.println("Please choose one of the following options: ");
        System.out.println("0 - End session");
        System.out.println("1 - Sign in as administrator");
        System.out.println("2 - Sign in as client");
    }
    public static void printWelcomeMessage()
    {
        System.out.println("Welcome to the rent a car app!");
    }
    public static void printAdministratorMenu()
    {
        System.out.println("Please chose one of the following options:");
        System.out.println("0 - End session");
        System.out.println("1 - Register a client");
        System.out.println("2 - Display all clients");
        System.out.println("3 - Register a car");
        System.out.println("4 - Display all cars");
        System.out.println("5 - Register a review");
        System.out.println("6 - Display all reviews");
        System.out.println("7 - Display all available cars");
        System.out.println("8 - Rent a car");
    }

    public static void printClientMenu()
    {
        System.out.println("Please chose one of the following options:");
        System.out.println("0 - End session");
        System.out.println("1 - Register as a client");
        System.out.println("2 - Register a car");
        System.out.println("3 - Display all cars");
        System.out.println("4 - Register a review");
        System.out.println("5 - Display all reviews");
        System.out.println("6 - Display all available cars");
        System.out.println("7 - Rent a car");
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
    public static void displayAllClients(ClientDAO clientDAO)
    {
        System.out.println(clientDAO.findAllClients().toString());
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
        System.out.print("Este disponibila? (d/n)");
        char disponibila = scanner.next().charAt(0);
        if(disponibila == 'd')
        {
            car.setDisponibila(true);
        } else {
            car.setDisponibila(false);
        }

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
    //Option 6
    public static void displayAllReviews(ReviewDAO reviewDAO)
    {
        System.out.println(reviewDAO.findAllReviews().toString());

    }
    //Option 8
    public static Rent registerRent(CarDAO carDAO, ClientDAO clientDAO)
    {
        Rent rent = new Rent();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alegeti masina dorita");
        List<Car> cars = carDAO.findAllAvailableCars();
        for (Car car: cars) {
            System.out.println(cars.indexOf(car) + " - " + car);
        }
        try {
            rent.setCar(cars.get(scanner.nextInt()));
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Va rugam aveti grija ce tastati!");
            rent.setCar(cars.get(scanner.nextInt()));
        }
        System.out.println("Introduceti data de inceput (YYYY-MM-DD): ");
        rent.setStartDate(LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("Introduceti data de finalizare (YYYY-MM-DD): ");
        rent.setEndDate(LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("Introduceti CNP-ul clientului: ");
        String cnp = scanner.next();
        Client client = clientDAO.findClientByCnp(cnp);
        if(client == null)
        {
            System.out.println("Clientul nu exista, va rugam sa introduceti datele lui.");
            client = registerClient();
            clientDAO.createClient(client);
        }
        rent.setClient(client);
        rent.setPrice();
        rent.setStare();
        carDAO.updateCar(rent.getCar());
        rent.setDepozit();
        System.out.println("S-a facut inregistrarea");
        return rent;
    }

}
