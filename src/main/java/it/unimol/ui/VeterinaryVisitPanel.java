package it.unimol.ui;

import it.unimol.app.*;
import it.unimol.app.exceptions.AnimalNotExists;
import it.unimol.app.managers.AnimalsManager;

import java.time.LocalDate;
import java.util.Scanner;

public class VeterinaryVisitPanel implements Panel {

    private AnimalsManager animalsManager;

    public VeterinaryVisitPanel(AnimalsManager animalsManager) {
        this.animalsManager = animalsManager;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the animal's ID:");
        int animalId = Integer.parseInt(sc.nextLine());

        try {
            animalsManager.findAnimalByID(animalId);
        } catch (AnimalNotExists animalNotExists) {
            System.out.println("Animal not found");
            return;
        }

        System.out.println("Enter the date of the visit:");
        LocalDate visitDate = LocalDate.parse(sc.nextLine());

        System.out.println("Enter the new Veterinary's name and surname:");
        String nameSurname = sc.nextLine();

        System.out.println("Enter the new Veterinary's diagnosis:");
        String diagnosis = sc.nextLine();

        System.out.println("Enter the new Veterinary's prescription:");
        String prescriptions = sc.nextLine();

        System.out.println("Enter the cost of the visit:");
        Float visitCost = Float.parseFloat((sc.nextLine()));

        System.out.println("City:");
        String city = sc.nextLine();

        VeterinaryVisit newVisit = new VeterinaryVisit(visitDate,nameSurname,diagnosis,prescriptions,visitCost);

        animalsManager.registerNewVetVisit(animalId, newVisit);

        System.out.println("VISIT SUCCESSFULLY ADDED!");
    }
}
