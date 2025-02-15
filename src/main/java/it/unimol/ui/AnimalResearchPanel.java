package it.unimol.ui;

import it.unimol.app.Animal;
import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.exceptions.AnimalNotExists;
import it.unimol.app.managers.AnimalsManager;

import java.util.List;
import java.util.Scanner;

public class AnimalResearchPanel implements Panel{
    private static final int BY_ID = 1;
    private static final int BY_SPECIES= 2;
    private static final int BY_HEALTH = 3;
    private static final int BY_ADOPTION = 4;
    private static final int EXIT = 0;

    private AnimalsManager animalsManager;

    public AnimalResearchPanel(AnimalsManager animalsManager){
        this.animalsManager=animalsManager;
    }

    @Override
    public void start() {

        boolean exit = false;
        do {
            printMenu();
            exit = manageUserInput();

        } while (!exit);
    }

    private void printMenu() {
        System.out.println("**REASERCH PANEL**");

        System.out.println("Chose your option. Search an animal:");
        System.out.println("1) By ID");
        System.out.println("2) By species");
        System.out.println("3) By health status");
        System.out.println("4) By adoption status");
        System.out.println("0) EXIT");

        System.out.println("Choice:");
    }

    public boolean manageUserInput() {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());

        do {
            switch (input) {
                case BY_ID:
                    searchById();
                    return false;

                case BY_SPECIES:
                    searchBySpecies();
                    return false;

                case BY_HEALTH:
                    searchByHealthConditions();
                    return false;

                case BY_ADOPTION:
                    searchByAdoptionStatus();
                    return false;

                case EXIT:
                    return true;

                default:
                    System.out.println("Insert a valid number.");
                    return false;
            }
        } while (input < 0 || input > 5);
    }

    private void searchById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an animal id:");
        int id = Integer.parseInt(sc.nextLine());

        try{
            System.out.println(animalsManager.findAnimalByID(id).toString());
        }catch(AnimalNotExists animalNotExists){
            System.out.println("Animal not found");
        }
    }

    private void searchBySpecies() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an animal species:");
        String species = sc.nextLine();

        List<Animal> animalsFound;
        try{
            animalsFound=animalsManager.findAnimalsBySpecies(species);
        }catch(AnimalNotExists animalNotExists){
            System.out.println("Any animals of this species!");
            return;
        }

        for (Animal animal : animalsFound){
            System.out.println(animal.toString());
        }
    }

    private void searchByHealthConditions() {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        HealthStatus healthStatus = HealthStatus.GOOD;
        System.out.println("Choose an animal health status.");

        do{
        System.out.println("1) EXCELLENT");
        System.out.println("2) GOOD");
        System.out.println("2) CRITICAL");
        System.out.println("Choice:");
        choice = Integer.parseInt(sc.nextLine());


            switch (choice) {
                case 1:
                    healthStatus = HealthStatus.EXCELLENT;
                    break;
                case 2:
                    healthStatus = HealthStatus.GOOD;
                    break;
                case 3:
                    healthStatus = HealthStatus.CRITICAL;
                    break;

                default:
                    System.out.println("Chose a correct option!");
            }

        }while (choice < 1 || choice > 3);


        List<Animal> animalsFound;

        try{
            animalsFound=animalsManager.findAnimalsByHealthStatus(healthStatus);
        }catch(AnimalNotExists animalNotExists){
            System.out.println("Any animals found!");
            return;
        }

        System.out.println("Here are the animals in this health condition:" + healthStatus.getDescription());
        for (Animal animal : animalsFound){
            System.out.println(animal.toString());
        }

    }

    private void searchByAdoptionStatus() {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        AdoptionStatus adoptionStatus = AdoptionStatus.WAITING;
        System.out.println("Choose an animal adoption status.");

        do {
            System.out.println("1) ADOPTED");
            System.out.println("2) AVAILABLE");
            System.out.println("2) WAITING");
            System.out.println("Choice:");
            choice = Integer.parseInt(sc.nextLine());


            switch (choice) {
                case 1:
                    adoptionStatus = AdoptionStatus.ADOPTED;
                    break;
                case 2:
                    adoptionStatus = AdoptionStatus.AVAILABLE;
                    break;
                case 3:
                    adoptionStatus = AdoptionStatus.WAITING;
                    break;

                default:
                    System.out.println("Chose a correct option!");
            }

        } while (choice < 1 || choice > 3);


        List<Animal> animalsFound;

        try {
            animalsFound = animalsManager.findAnimalsByAdoptionStatus(adoptionStatus);
        } catch (AnimalNotExists animalNotExists) {
            System.out.println("Any animals found!");
            return;
        }

        System.out.println("Here are the animals in this adoption condition:" + adoptionStatus);
        for (Animal animal : animalsFound) {
            System.out.println(animal.toString());
        }
    }

}
