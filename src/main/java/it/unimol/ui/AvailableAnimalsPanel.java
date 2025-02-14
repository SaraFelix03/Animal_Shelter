package it.unimol.ui;

import it.unimol.app.Animal;
import it.unimol.app.exceptions.AnimalNotExists;
import it.unimol.app.managers.AnimalsManager;
import it.unimol.ui.Panel;

import java.util.List;

public class AvailableAnimalsPanel implements Panel {
    private AnimalsManager animalsManager;

    public AvailableAnimalsPanel(AnimalsManager animalsManager){
        this.animalsManager = animalsManager;
    }

    public void start(){
        List<Animal> availableAnimals;
        try{
            availableAnimals = animalsManager.findAvailableAnimals();
        }catch(AnimalNotExists animalNotExists){
            System.out.println("Any animals availables!");
            return;
        }

        for (Animal animal : availableAnimals){
            System.out.println(animal.toString());
        }
    }
}
