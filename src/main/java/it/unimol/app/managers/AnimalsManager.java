package it.unimol.app.managers;

import it.unimol.app.*;
import it.unimol.app.enumerations.*;
import it.unimol.app.exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.unimol.app.enumerations.AdoptionStatus.WAITING;


/**
 * Manages animal records, including adoption and medical history.
 * Supports adding, removing, and searching animals,
 * handling adoptions, and storing medical visit records.
 *
 * This class follows the Singleton pattern.
 *
 * @author Sara F.C.
 * @version 1.0
 */
public class AnimalsManager implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Name of the serialization file used for persistence. */
    private static String serializationFileName;

    /** Counter for generating unique animal IDs. */
    private static int idCounter;

    /** Singleton instance of AnimalsManager. */
    private static AnimalsManager instance;

    /** List of registered animals. */
    private List<Animal> animals;

    /** Manager for adoption operations. */
    private static AdoptionManager adoptionManager;

    /** Manager for tracking medical history of animals. */
    private static MedicalHistoryManager medicalHistoryManager;

    /**
     * Private constructor for initializing the AnimalsManager.
     *
     * @param serializationFileName The name of the file used for data persistence.
     */
    private AnimalsManager(String serializationFileName) {
        idCounter = 0;
        AnimalsManager.serializationFileName = serializationFileName;
        this.animals = new ArrayList<>(100);
        adoptionManager = AdoptionManager.getInstance();
        medicalHistoryManager = MedicalHistoryManager.getInstance();
    }

    /**
     * Returns the singleton instance of AnimalsManager.
     * If no instance exists, a new one is created.
     *
     * @param serializationFileName The filename for serialization.
     * @return The singleton instance of AnimalsManager.
     */
    public static AnimalsManager getInstance(String serializationFileName) {
        if (instance == null) {
            instance = new AnimalsManager(serializationFileName);
        }

        return instance;
    }


    /**
     * Resets the AnimalsManager instance and clears the ID counter.
     */
    public static void resetInstance() {
        instance = null;
        idCounter = 0;
    }

    /**
     * Generates a new unique ID for an animal.
     *
     * @return A new unique ID.
     */
    public static int getNewID() {
        ++idCounter;
        return idCounter;
    }

    /**
     * Retrieves the last assigned animal ID.
     *
     * @return The last assigned ID.
     */
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


    /**
     * Initializes the AnimalsManager by loading data from the serialized file.
     *
     * @throws IOException If an error occurs during file reading.
     */
    public static void AnimalsManagerInitialize() throws IOException {
        instance = loadManager();
    }

    /**
     * Saves the current state of AnimalsManager to a file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    private void saveManager() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(serializationFileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(this);
        }
    }

    /**
     * Loads the AnimalsManager instance from a file.
     * If the file does not exist, a new instance is created.
     *
     * @return The loaded AnimalsManager instance.
     * @throws IOException If an error occurs during file reading.
     */
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


    /**
     * Adds a new animal to the system and saves the updated state.
     *
     * @param animal The animal to be added.
     * @throws AnimalAlreadyRegistered If the animal is already registered.
     * @throws IOException If an error occurs while saving data.
     */
    public void addAnimal(Animal animal) throws AnimalAlreadyRegistered, IOException {
        if (this.animals.contains(animal)) {
            throw new AnimalAlreadyRegistered();
        } else {
            this.animals.add(animal);
            instance.saveManager();
        }
    }


    /**
     * Finds an animal by its unique ID.
     *
     * @param id The animal's ID.
     * @return The corresponding Animal object.
     * @throws AnimalNotExists If the animal is not found.
     */
    public Animal findAnimalByID(int id) throws AnimalNotExists {
        return (Animal)this.animals.stream().filter((animal) -> {
            return animal.getID() == id;
        }).findFirst().orElseThrow(() -> {
            return new AnimalNotExists("Animal with ID " + id + " does not exist");
        });
    }


    /**
     * Finds animals by species.
     *
     * @param species The species name.
     * @return A list of animals matching the species.
     * @throws AnimalNotExists If no animals of the species exist.
     */
    public List findAnimalsBySpecies(String species) throws AnimalNotExists {
        List <Animal> animalsFound = this.animals.stream().filter(animal -> animal.getSpecies().
                equalsIgnoreCase(species)).collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }


    /**
     * Finds animals by health status.
     *
     * @param healthStatus The health status to filter.
     * @return A list of animals matching the health status.
     * @throws AnimalNotExists If no animals with the given status exist.
     */
    public List findAnimalsByHealthStatus(HealthStatus healthStatus) throws AnimalNotExists {
        List<Animal> animalsFound = this.animals.stream().filter(animal -> animal.getHealthStatus() == healthStatus)
                .collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }

    /**
     * Finds animals by adoption status.
     *
     * @param adoptionStatus The adoption status to filter.
     * @return A list of animals with the specified adoption status.
     * @throws AnimalNotExists If no animals match the status.
     */
    public List findAnimalsByAdoptionStatus(AdoptionStatus adoptionStatus) throws AnimalNotExists {
        List<Animal> animalsFound = this.animals.stream().filter(animal -> animal.getAdoptionStatus() == adoptionStatus)
                .collect(Collectors.toList());
        if (animalsFound.isEmpty()) {
            throw new AnimalNotExists();
        } else {
            return animalsFound;
        }
    }

    /**
     * Removes an animal from the system using its ID.
     *
     * @param id The ID of the animal to remove.
     * @throws AnimalNotExists If the animal does not exist.
     * @throws IOException If an error occurs while saving data.
     */
    public void removeAnimalByID(int id) throws AnimalNotExists, IOException {
        Animal animalFound = this.findAnimalByID(id);
        this.animals.remove(animalFound);
        instance.saveManager();
    }

    /**
     * Finds all animals that are available for adoption.
     *
     * @return A list of animals that have the status {@code AdoptionStatus.AVAILABLE}.
     * @throws AnimalNotExists If no available animals are found.
     */
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


    /**
     * Registers a new adoption for an animal.
     *
     * @param animal   The animal being adopted.
     * @param adoption The adoption details.
     * @throws AnimalAlreadyAdoptedException If the animal is already adopted.
     * @throws IOException If an error occurs while saving data.
     */
    public void registerAdoption(Animal animal, Adoption adoption) throws AnimalAlreadyAdoptedException, IOException {
        adoptionManager.registerNewAdoption(animal.getID(), adoption);
        animal.setAdoptionStatus(AdoptionStatus.ADOPTED);
        saveManager();
    }

    /**
     * Registers a new veterinary visit for a specific animal.
     * Updates the medical history and attempts to save the manager state.
     *
     * @param animalID The ID of the animal that received the veterinary visit.
     * @param visit The {@code VeterinaryVisit} object containing details of the visit.
     */
    public void registerNewVetVisit(int animalID, VeterinaryVisit visit) {
        medicalHistoryManager.registerNewVisit(animalID, visit);
        try {
            this.saveManager();
        } catch (IOException ioException) {
            System.out.println("IOException in AnimalsManager");
        }
    }


}
