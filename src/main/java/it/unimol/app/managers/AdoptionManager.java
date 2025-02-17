package it.unimol.app.managers;

import it.unimol.app.Adopter;
import it.unimol.app.Adoption;
import it.unimol.app.exceptions.AdopterAlreadyRegistered;
import it.unimol.app.exceptions.AdopterNotExists;
import it.unimol.app.exceptions.AnimalAlreadyAdoptedException;
import it.unimol.app.exceptions.AnimalNotAdopted;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the adoption process of animals.
 * It allows registering new adopters and adoptions,
 * and provides methods to retrieve adoption-related information.
 *
 * This class makes use of the Singleton pattern.
 *
 * @author Sara F.C.
 * @version 1.0
 */
public class AdoptionManager implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Counter to assign unique IDs to adopters. */
    private static int adopterIdCounter;

    /** Singleton instance of AdoptionManager. */
    private static AdoptionManager instance;

    /** List of registered adopters. */
    private static List<Adopter> adopterList;

    /** Map linking each animal ID to its corresponding adoption. */
    private static Map<Integer, Adoption> adoptions;

    /**
     * Private constructor that initializes the list of adopters
     * and the adoption mapping.
     */
    public AdoptionManager() {
        adoptions = new HashMap<Integer, Adoption>();
        adopterList = new ArrayList<Adopter>();
    }

    /**
     * Returns the singleton instance of AdoptionManager.
     * If no instance exists, it creates a new one.
     *
     * @return The singleton instance of AdoptionManager.
     */
    public static AdoptionManager getInstance() {
        if (instance == null) {
            instance = new AdoptionManager();
        }

        return instance;
    }

    /**
     * Resets the instance of AdoptionManager and clears its data.
     * This method is useful for testing to start with a clean state.
     */
    public static void resetInstance() {
        instance = null;
        adopterIdCounter = 0;
        adopterList.clear();
        adoptions.clear();
    }

    /**
     * Generates a new unique adopter ID.
     *
     * @return A new unique adopter ID.
     */
    public static int getNewAdopterID() {
        adopterIdCounter++;
        return adopterIdCounter;
    }


    /**
     * Retrieves the last assigned adopter ID.
     *
     * @return The ID of the last registered adopter.
     */
    public static int getLastAdopterID() {
        return adopterIdCounter;
    }

    /**
     * Returns the total number of registered adoptions.
     *
     * @return The total number of adoptions.
     */
    protected int getTotalAdoptions() {
        return adoptions.size();
    }

    /**
     * Registers a new adopter if they are not already in the list.
     *
     * @param adopter The Adopter object to register.
     * @throws AdopterAlreadyRegistered If the adopter is already registered.
     */
    public void registerNewAdopter(Adopter adopter) throws AdopterAlreadyRegistered {
        if (!adopterList.contains(adopter)) {
            adopterList.add(adopter);
            return;
        }
        throw new AdopterAlreadyRegistered();
    }

    /**
     * Registers a new adoption for an animal.
     *
     * @param animalId The ID of the adopted animal.
     * @param adoption The Adoption object containing adoption details.
     * @throws AnimalAlreadyAdoptedException If the animal has already been adopted.
     */
    public void registerNewAdoption(int animalId, Adoption adoption) throws AnimalAlreadyAdoptedException {
        if (!adoptions.isEmpty() && adoptions.containsKey(animalId)) {
            throw new AnimalAlreadyAdoptedException();
        }
        adoptions.put(animalId, adoption);
    }

    /**
     * Retrieves adoption details for a given animal ID.
     *
     * @param animalID The ID of the animal.
     * @return The corresponding Adoption object.
     * @throws AnimalNotAdopted If the animal has not been adopted.
     */
    public Adoption findAdoptionInfo(int animalID) throws AnimalNotAdopted {
        if (!adoptions.containsKey(animalID)) {
            throw new AnimalNotAdopted();
        }
        return adoptions.get(animalID);
    }


    /**
     * Finds an adopter by their ID.
     *
     * @param id The adopter's ID.
     * @return The corresponding Adopter object.
     * @throws AdopterNotExists If no adopter with the specified ID exists.
     */
    public Adopter findAdopterByID(int id) throws AdopterNotExists {
        return adopterList.stream()
                .filter(adopter -> adopter.getId() == id)
                .findFirst()
                .orElseThrow(AdopterNotExists::new);
    }

}
