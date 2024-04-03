package org.example;

import org.example.cars.Car;
import org.example.clients.Client;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

    }

    public static void printWelcomeMenu()
    {
        System.out.println("Welcome to the rent a car app!");
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

    public static Client registerAClient()
    {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        System.out.print("Name: ");
        client.setName(scanner.next());
        System.out.print("CNP: ");
        client.setCnp(scanner.next());
        return client;
    }
    public static Car registerACar()
    {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();
        System.out.println("VIN: ");
        car.setVin(scanner.next());
        System.out.println("Marca: ");
        car.setMarca(scanner.next());
        return car;
    }
}
