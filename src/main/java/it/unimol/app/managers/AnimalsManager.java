package it.unimol.app.managers;

import it.unimol.app.Animal;
import it.unimol.app.VeterinaryVisit;
import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.exceptions.AnimalAlreadyRegistered;
import it.unimol.app.exceptions.AnimalNotExists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.unimol.app.enumerations.AdoptionStatus.WAITING;

public class AnimalsManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int idCounter;
    private static AnimalsManager instance;
    private List<Animal> animals;
    private static AdoptionManager adoptionManager;
    private static MedicalHistoryManager medicalHistoryManager;

    private AnimalsManager() {
        idCounter = 0;
        this.animals = new ArrayList(100);
        adoptionManager = AdoptionManager.getInstance();
        medicalHistoryManager = MedicalHistoryManager.getInstance();
    }

    public static AnimalsManager getInstance() {
        if (instance == null) {
            instance = new AnimalsManager();
        }

        return instance;
    }

    public static int getNewID() {
        ++idCounter;
        return idCounter;
    }

    public static int getLastID() {
        return idCounter;
    }

    public int getTotalAnimals(){
        return this.animals.size();
    }

    public int getTotalAdoptions(){
        return this.adoptionManager.getTotalAdoptions();
    }

    public int getTotalAnimalsWaitingForAdoption() {
        return (int) this.animals.stream().filter(animal -> animal.getAdoptionStatus() == WAITING).count();
    }

    public int getTotalAnimalsCriticalCondition() {
        return (int) this.animals.stream().filter(animal -> animal.getHealthStatus() == HealthStatus.CRITICAL).count();
    }

    public static void AnimalsManagerInitialize() throws IOException {
        instance = loadManager();
    }

    private void saveManager() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Shelter.sr");

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            try {
                objectOutputStream.writeObject(this);
            } catch (Throwable var7) {
                try {
                    objectOutputStream.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            objectOutputStream.close();
        } catch (Throwable var8) {
            try {
                fileOutputStream.close();
            } catch (Throwable var5) {
                var8.addSuppressed(var5);
            }

            throw var8;
        }

        fileOutputStream.close();
    }

    public static AnimalsManager loadManager() throws FileNotFoundException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("Shelter.sr");

            AnimalsManager var3;
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                try {
                    Object o = objectInputStream.readObject();
                    var3 = (AnimalsManager)o;
                } catch (Throwable var6) {
                    try {
                        objectInputStream.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }

                    throw var6;
                }

                objectInputStream.close();
            } catch (Throwable var7) {
                try {
                    fileInputStream.close();
                } catch (Throwable var4) {
                    var7.addSuppressed(var4);
                }

                throw var7;
            }

            fileInputStream.close();
            return var3;
        } catch (FileNotFoundException var8) {
            return new AnimalsManager();
        } catch (ClassNotFoundException var9) {
            return null;
        }
    }

    public void addAnimal(Animal animal) throws AnimalAlreadyRegistered, IOException {
        if (this.animals.contains(animal)) {
            throw new AnimalAlreadyRegistered();
        } else {
            this.animals.add(animal);
            instance.saveManager();
        }
    }

    public void removeAnimal(Animal animal) throws AnimalNotExists, IOException {
        if (!this.animals.contains(animal)) {
            throw new AnimalNotExists();
        } else {
            this.animals.remove(animal);
            instance.saveManager();
        }
    }

    public Animal findAnimalByID(int id) throws AnimalNotExists {
        return (Animal)this.animals.stream().filter((animal) -> {
            return animal.getID() == id;
        }).findFirst().orElseThrow(() -> {
            return new AnimalNotExists("Animal with ID " + id + " does not exist");
        });
    }

    public void removeAnimalByID(int id) throws AnimalNotExists, IOException {
        Animal animalFound = this.findAnimalByID(id);
        this.animals.remove(animalFound);
        instance.saveManager();
    }

    public List<Animal> findAvailableAnimals() throws AnimalNotExists {
        List<Animal> availableAnimals = (List)this.animals.stream().filter((animal) -> {
            return animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE;
        }).collect(Collectors.toList());
        if (availableAnimals.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return availableAnimals;
        }
    }

    public void registerNewVetVisit(int animalID, VeterinaryVisit visit){
        medicalHistoryManager.registerNewVisit(animalID, visit);
        try{
            this.saveManager();
        }catch(IOException ioException){
            System.out.println("IOException in AnimalsManager");
        }
    }


}
