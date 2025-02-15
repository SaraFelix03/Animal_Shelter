package it.unimol.ui;

import it.unimol.app.Animal;
import it.unimol.app.enumerations.*;
import it.unimol.app.exceptions.AnimalAlreadyRegistered;
import it.unimol.app.managers.AnimalsManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class AnimalRegistrationPanel implements Panel{
    private AnimalsManager animalsManager;

    public AnimalRegistrationPanel(AnimalsManager animalsManager) {
        this.animalsManager = animalsManager;
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the animal's name:");
        String name = sc.nextLine();

        System.out.println("Enter the animal's species:");
        String species = sc.nextLine();

        System.out.println("Enter the animal's age:");
        String age = sc.nextLine();

        System.out.println("Enter the animal's admission date:");
        LocalDate admissionDate = LocalDate.parse(sc.nextLine());

        HealthStatus healthStatus = manageHealthStatusOptions();

        AdoptionStatus adoptionStatus = adoptionStatusOptions();

        System.out.println("Further information:");
        String furtherInfo = sc.nextLine();

        Animal newAnimal = new Animal(name,species,age,admissionDate,healthStatus,adoptionStatus,furtherInfo);
        try{
            animalsManager.addAnimal(newAnimal);
            System.out.println("Animal added!");
        }catch(AnimalAlreadyRegistered e){
            System.out.println("Animal already registered!");
        }catch(IOException e ){
            System.out.println("IO Exception");
        }

    }

    private HealthStatus manageHealthStatusOptions(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the health status:");
        System.out.println("1 - Excellent");
        System.out.println("2 - Good");
        System.out.println("3 - Critical");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        HealthStatus status = null;

        switch (choice) {
            case 1 -> status = HealthStatus.EXCELLENT;
            case 2 -> status = HealthStatus.GOOD;
            case 3 -> status = HealthStatus.CRITICAL;
            default -> {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                return status;
            }
        }
        return status;
    }

    private AdoptionStatus adoptionStatusOptions(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the adoption status:");
        System.out.println("1 - Available");
        System.out.println("2 - Adopted");
        System.out.println("3 - Waiting");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());
        AdoptionStatus status = null;

        switch (choice) {
            case 1 -> status = AdoptionStatus.AVAILABLE;
            case 2 -> status = AdoptionStatus.ADOPTED;
            case 3 -> status = AdoptionStatus.WAITING;
            default -> {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                scanner.close();
            }
        }
        return status;
    }
}
