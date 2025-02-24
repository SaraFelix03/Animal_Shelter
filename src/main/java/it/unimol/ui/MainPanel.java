package it.unimol.ui;

import it.unimol.app.managers.AnimalsManager;

import java.io.IOException;
import java.util.Scanner;

public class MainPanel implements Panel {
    private static final int ADD_NEW_ANIMAL = 1;
    private static final int AVAIBLE_ANIMALS = 2;
    private static final int REGISTER_ADOPTION = 3;
    private static final int RECORD_VISIT = 4;
    private static final int SHOW_STATISTICS = 5;
    private static final int SEARCH_ANIMALS = 6;
    private static final int SHELTER_EXPENCES = 7;
    private static final int ESCI = 0;

    private static MainPanel instance;
    private AnimalsManager animalsManager;

    private MainPanel() {
        animalsManager = AnimalsManager.getInstance("Shelter.sr");
    }

    public static MainPanel getInstance() {
        if (instance == null) {
            instance = new MainPanel();
        }
        return instance;
    }

    @Override
    public void start() {

        try {
            AnimalsManager.AnimalsManagerInitialize();
        } catch (IOException e) {
            System.err.println("Non è stato possibile leggere il file. Eliminare il file.");
            System.exit(-1);
        }

        animalsManager = AnimalsManager.getInstance("Shelter.sr");

        boolean exit = false;
        do {
            printMenu();
            exit = manageUserInput();

        } while (!exit);
    }

    private void printMenu() {
        System.out.println("///  WELCOME!  ///");

        System.out.println("Chose your operation:");
        System.out.println("1) Add a new animal");
        System.out.println("2) View Available Animals");
        System.out.println("3) Register Adoption");
        System.out.println("4) Record Veterinary Visit");
        System.out.println("5) Generate Shelter Statistics");
        System.out.println("6) Search Animals");
        System.out.println("7) Show veterinary expenses");
        System.out.println("0) EXIT");

        System.out.println("Choice:");
    }

    public boolean manageUserInput() {
        Scanner sc = new Scanner(System.in);
        int input = Integer.parseInt(sc.nextLine());

        do {
            switch (input) {
                case ADD_NEW_ANIMAL:
                    addNewAnimal();
                    return false;

                case AVAIBLE_ANIMALS:
                    showAvaibleAnimals();
                    return false;

                case REGISTER_ADOPTION:
                    registerNewAdoption();
                    return false;

                case RECORD_VISIT:
                    recordNewVisit();
                    return false;

                case SHOW_STATISTICS:
                    showShelterStatistics();
                    return false;

                case SEARCH_ANIMALS:
                    searchAnimals();
                    return false;

                case SHELTER_EXPENCES:
                    showExpences();
                    return false;


                case ESCI:
                    return true;

                default:
                    System.out.println("Insert a valid number.");
                    return false;
            }
        } while (input < 0 || input > 5);
    }

    private void addNewAnimal() {
        AnimalRegistrationPanel animalRegistrationPanel = new AnimalRegistrationPanel(animalsManager);
        animalRegistrationPanel.start();
    }

    private void showAvaibleAnimals() {
        AvailableAnimalsPanel availableAnimalsPanel = new AvailableAnimalsPanel(animalsManager);
        availableAnimalsPanel.start();
    }

    private void registerNewAdoption() {
        AdoptionPanel adoptionPanel = new AdoptionPanel(animalsManager);
        adoptionPanel.start();
    }

    private void recordNewVisit() {
        VeterinaryVisitPanel veterinaryVisitPanel = new VeterinaryVisitPanel(animalsManager);
        veterinaryVisitPanel.start();
    }

    private void showShelterStatistics() {
        ShelterStatisticsPanel shelterStatisticsPanel = new ShelterStatisticsPanel(animalsManager);
        shelterStatisticsPanel.start();
    }

    private void searchAnimals() {
        AnimalResearchPanel animalResearchPanel = new AnimalResearchPanel(animalsManager);
        animalResearchPanel.start();
    }

    private void showExpences() {
        ExpencesPanel expencesPanel = new ExpencesPanel(animalsManager);
        expencesPanel.start();
    }

}