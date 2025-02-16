package it.unimol.app.managers;

import it.unimol.app.*;
import it.unimol.app.enumerations.*;
import it.unimol.app.exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.unimol.app.enumerations.AdoptionStatus.WAITING;

public class AnimalsManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private static String serializationFileName;

    private static int idCounter;
    private static AnimalsManager instance;
    private List<Animal> animals;
    private static AdoptionManager adoptionManager;
    private static MedicalHistoryManager medicalHistoryManager;

    private AnimalsManager(String serializationFileName) {
        idCounter = 0;
        AnimalsManager.serializationFileName = serializationFileName;
        this.animals = new ArrayList<>(100);
        adoptionManager = AdoptionManager.getInstance();
        medicalHistoryManager = MedicalHistoryManager.getInstance();
    }

    public static AnimalsManager getInstance(String serializationFileName) {
        if (instance == null) {
            instance = new AnimalsManager(serializationFileName);
        }

        return instance;
    }

    public static void resetInstance() {
        instance = null;
        idCounter = 0;
    }

    public static int getNewID() {
        ++idCounter;
        return idCounter;
    }

    public static int getLastID() {
        return idCounter;
    }

    public int getTotalAnimals() {
        return this.animals.size();
    }

    public int getTotalAdoptions() {
        return adoptionManager.getTotalAdoptions();
    }

    public int getTotalAnimalsWaitingForAdoption() {
        return (int) this.animals.stream().filter(animal -> animal.getAdoptionStatus() == WAITING).count();
    }

    public int getTotalAnimalsCriticalCondition() {
        return (int) this.animals.stream().filter(animal -> animal.getHealthStatus() == HealthStatus.CRITICAL).count();
    }

    public MedicalHistoryManager getMedicalHistoryManager() {
        return medicalHistoryManager;
    }

    public AdoptionManager getAdoptionManager() {
        return adoptionManager;
    }

    public void createNewSerializationFile(String fileName) {
        serializationFileName = fileName;
    }

    public static void AnimalsManagerInitialize() throws IOException {
        instance = loadManager();
    }

    private void saveManager() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(serializationFileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(this);
        }
    }


    public static AnimalsManager loadManager() throws IOException {
        File file = new File(serializationFileName);

        if (!file.exists()) {
            return new AnimalsManager(serializationFileName);
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            return (AnimalsManager) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
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


    public Animal findAnimalByID(int id) throws AnimalNotExists {
        return (Animal)this.animals.stream().filter((animal) -> {
            return animal.getID() == id;
        }).findFirst().orElseThrow(() -> {
            return new AnimalNotExists("Animal with ID " + id + " does not exist");
        });
    }

    public List findAnimalsBySpecies(String species) throws AnimalNotExists {
        List <Animal> animalsFound = this.animals.stream().filter(animal -> animal.getSpecies().
                equalsIgnoreCase(species)).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }

    public List findAnimalsByHealthStatus(HealthStatus healthStatus) throws AnimalNotExists {
        List<Animal> animalsFound = this.animals.stream().filter(animal -> animal.getHealthStatus() == healthStatus)
                .collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }

    public List findAnimalsByAdoptionStatus(AdoptionStatus adoptionStatus) throws AnimalNotExists {
        List<Animal> animalsFound = this.animals.stream().filter(animal -> animal.getAdoptionStatus() == adoptionStatus)
                .collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }


    public void removeAnimalByID(int id) throws AnimalNotExists, IOException {
        Animal animalFound = this.findAnimalByID(id);
        this.animals.remove(animalFound);
        instance.saveManager();
    }

    public List<Animal> findAvailableAnimals() throws AnimalNotExists {
        List availableAnimals = this.animals.stream().filter((animal) -> {
            return animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE;
        }).collect(Collectors.toList());
        if (availableAnimals.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return availableAnimals;
        }
    }

    public void registerAdoption(Animal animal, Adoption adoption) throws AnimalAlreadyAdoptedException, IOException {
        adoptionManager.registerNewAdoption(animal.getID(), adoption);
        animal.setAdoptionStatus(AdoptionStatus.ADOPTED);
        saveManager();
    }

    public void registerNewVetVisit(int animalID, VeterinaryVisit visit) {
        medicalHistoryManager.registerNewVisit(animalID, visit);
        try {
            this.saveManager();
        } catch (IOException ioException) {
            System.out.println("IOException in AnimalsManager");
        }
    }


}
