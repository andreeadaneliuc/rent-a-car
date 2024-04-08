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
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the rent a car app!");
    }

    public static void printUserTypeSelectionMenu() {
        System.out.println("Please choose one of the following options: ");
        System.out.println("0 - End session");
        System.out.println("1 - Sign in as administrator");
        System.out.println("2 - Sign in as client");
    }

    public static void printAdministratorMenu() {
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
        System.out.println("9 - Delete a client");
    }

    public static void printClientMenu() {
        System.out.println("Please chose one of the following options:");
        System.out.println("0 - End session");
        System.out.println("1 - Register as a client");
        System.out.println("2 - Register a car");
        System.out.println("3 - Display all cars");
        System.out.println("4 - Register a review");
        System.out.println("5 - Display all reviews");
        System.out.println("6 - Display all available cars");
        System.out.println("7 - Rent a car");
        System.out.println("8 - Delete a review");
    }

    public static String scanareNext() {
        Scanner scanner = new Scanner(System.in);
        String scanat = "";
        try {
            scanat = scanner.next();
        } catch (IllegalArgumentException e) {
            System.out.println("Te rog reintrodu datele");
        }
        return scanat;
    }

    public static String scanareNextLine() {
        Scanner scanner = new Scanner(System.in);
        String scanat = "";
        try {
            scanat = scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Te rog reintrodu datele");
        }
        return scanat;
    }

    //Option 1
    public static Client registerClient() {
        Client client = new Client();
        boolean ok = true;
        String name = "";
        String cnp = "";
        while (ok) {
            System.out.print("Name: ");
            name = scanareNextLine();
            if (!name.isBlank()) {
                ok = false;
            }
        }
        client.setName(name);
        ok = true;
        while (ok) {
            System.out.print("CNP: ");
            cnp = scanareNext();
            if (Client.isCnpValid(cnp)) {
                ok = false;
            } else {
                System.out.println("CNP is not valid");
            }
        }
        client.setCnp(cnp);

        return client;
    }

    public static void displayAllClients(ClientDAO clientDAO) {
        System.out.println(clientDAO.findAllClients().toString());
    }

    //Option 3
    public static Car registerCar() {
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
        if (disponibila == 'd') {
            car.setDisponibila(true);
        } else {
            car.setDisponibila(false);
        }

        return car;
    }

    public static void displayAllCars(CarDAO carDAO) {
        System.out.println(carDAO.findAllCars().toString());

    }

    //Option 5
    public static Review registerReview() {
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
        if (rentToSearch == null) {
            System.out.print("Nu exista aceasta comanda");
        } else {
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
    public static void displayAllReviews(ReviewDAO reviewDAO) {
        System.out.println(reviewDAO.findAllReviews().toString());

    }

    //Option 8
    public static Rent registerRent(CarDAO carDAO, ClientDAO clientDAO) {
        Rent rent = new Rent();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alegeti masina dorita");
        List<Car> cars = carDAO.findAllAvailableCars();
        for (Car car : cars) {
            System.out.println(cars.indexOf(car) + " - " + car);
        }
        try {
            rent.setCar(cars.get(scanner.nextInt()));
        } catch (IndexOutOfBoundsException e) {
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
        if (client == null) {
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

    public static void deleteClient(ClientDAO clientDAO) {
        System.out.println("Introduceti cnp-ul clientului: ");
        String cnpScanat = Menu.scanareNext();
        if (Client.isCnpValid(cnpScanat)) {
            if (clientDAO.findClientByCnp(cnpScanat) != null) {
                clientDAO.deleteClient(clientDAO.findClientByCnp(cnpScanat));
                System.out.println("Clientul a fost sters cu succes!");
            } else {
                System.out.println("Clientul nu exista!");
            }
        } else {
            System.out.println("Cnp-ul nu este valid!");
        }
    }

    public static void deleteReview(ReviewDAO reviewDAO, ClientDAO clientDAO) {
        System.out.println("Introduceti cnp-ul : ");
        String cnpScanat = Menu.scanareNext();
        if (Client.isCnpValid(cnpScanat)) {
            if (clientDAO.findClientByCnp(cnpScanat) != null) {
                Client client = clientDAO.findClientByCnp(cnpScanat);
                int numar = 0;
                for (Rent rent : client.getRentsSet().stream().toList()) {
                    reviewDAO.findAllReviewsByClient(rent);
                    System.out.println(numar + " " + rent);
                    numar++;
                }
                System.out.println("Alegeti review-ul pe care doriti sa il stergeti: ");
                Scanner scanner = new Scanner(System.in);
                try {
                    reviewDAO.deleteReview(reviewDAO.getReviewByNrComanda(client.getRentsSet().stream().toList().get(scanner.nextInt()).getNrComanda()));
                    System.out.println("Review-ul ales a fost sters.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Numarul introdus este gresit.");
                }
            } else {
                System.out.println("Clientul nu exista!");
            }
        } else {
            System.out.println("Cnp-ul nu este valid!");
        }

    }
}
