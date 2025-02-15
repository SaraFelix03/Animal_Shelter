package it.unimol.ui;

import it.unimol.app.*;
import it.unimol.app.enumerations.ContractStatus;
import it.unimol.app.exceptions.*;
import it.unimol.app.managers.*;

import java.time.LocalDate;
import java.util.Scanner;

public class AdoptionPanel implements Panel{

    private AnimalsManager animalsManager;
    private AdoptionManager adoptionManager;

    public AdoptionPanel(AnimalsManager animalsManager, AdoptionManager adoptionManager){
        this.animalsManager = animalsManager;
        this.adoptionManager = adoptionManager;
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the animal's ID:");
        int animalId = Integer.parseInt(sc.nextLine());
        Animal adoptedAnimal;

        try{
            adoptedAnimal = animalsManager.findAnimalByID(animalId);
        }catch(AnimalNotExists animalNotExists){
            System.out.println("Animal not found");
            return;
        }

        printAdopterMenu();
        Adopter adopter;

        try{
            adopter=adopterMenuInputManager();
        }catch(AdopterNotExists adopterNotExists){
            System.out.println("Adopter not found!");
            return;
        }

        ContractStatus contractStatus=manageContractOptions();

        Adoption newAdoption = new Adoption(adoptedAnimal.getID(), adopter.getId(), LocalDate.now(),contractStatus);

        try{
            adoptionManager.registerNewAdoption(animalId,newAdoption);
        }catch(AnimalAlreadyAdoptedException animalAlreadyAdoptedException){
            System.out.println("Animal already adopted!");
        }

        adopter.addNewAdoptedAnimal(adoptedAnimal);

        System.out.println("ADOPTION SUCCESSFULLY ADDED!");
    }

    public void printAdopterMenu(){
        System.out.println("Choose an option:");
        System.out.println("1) Register a new Adopter");
        System.out.println("2) Register an existing Adopter");
    }


    public Adopter adopterMenuInputManager() throws AdopterNotExists {
        Scanner sc = new Scanner(System.in);
        int choice = Integer.parseInt(sc.nextLine());

        do {
            switch (choice) {
                case 1:
                    System.out.println("Enter the new Adopter's name:");
                    String name = sc.nextLine();

                    System.out.println("Enter the new Adopter's surname:");
                    String surname = sc.nextLine();

                    System.out.println("Enter the new Adopter's cdf:");
                    String cdf = sc.nextLine();

                    System.out.println("Enter the new Adopter's address.");
                    System.out.println("Street:");
                    String street = sc.nextLine();

                    System.out.println("Street number:");
                    String streetNumber = sc.nextLine();

                    System.out.println("City:");
                    String city = sc.nextLine();

                    System.out.println("Postal Code:");
                    String postalCode = sc.nextLine();

                    System.out.println("Country:");
                    String country = sc.nextLine();

                    Address adopterAddress= new Address(street,streetNumber,city,postalCode,country);

                    System.out.println("Enter the new Adopter's telephone:");
                    String telephone = sc.nextLine();

                    System.out.println("Enter the new Adopter's email:");
                    String email = sc.nextLine();

                    return new Adopter(name,surname,cdf,adopterAddress,telephone,email);

                case 2:
                    System.out.println("Enter the Adopter's ID:");
                    int adopterId = Integer.parseInt(sc.nextLine());
                    Adopter adopter;

                    adopter= adoptionManager.findAdopterByID(adopterId);

                    return adopter;

                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }while(true);
    }

    private ContractStatus manageContractOptions(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the contract status:");
        System.out.println("1 - Signed");
        System.out.println("2 - Unsigned");
        System.out.println("3 - In progress");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(scanner.nextLine());

        do {
            switch (choice) {
                case 1:
                    return ContractStatus.SIGNED;
                case 2:
                    return ContractStatus.UNSIGNED;
                case 3:
                    return ContractStatus.IN_PROGRESS;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }while(true);

    }
}

