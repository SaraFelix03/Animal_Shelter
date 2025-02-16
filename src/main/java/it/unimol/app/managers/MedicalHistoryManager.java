package it.unimol.app.managers;

import it.unimol.app.VeterinaryVisit;
import it.unimol.app.exceptions.NoRegistredVisitsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the medical history of animals by storing veterinary visits.
 * This class allows registering new visits and retrieving visit history.
 *
 * It follows the Singleton pattern to ensure a single instance manages all records.
 *
 * @author Sara F.C.
 * @version 1.0
 */
public class MedicalHistoryManager implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Singleton instance of MedicalHistoryManager. */
    private static MedicalHistoryManager instance;

    /** Map storing veterinary visits, where the key is the animal's ID and the value is a list of visits. */
    private static Map<Integer, List<VeterinaryVisit>> visitsHistory;

    /**
     * Private constructor that initializes the visits history storage.
     */
    private MedicalHistoryManager() {
        visitsHistory = new HashMap<Integer, List<VeterinaryVisit>>();
    }

    /**
     * Returns the singleton instance of MedicalHistoryManager.
     * If no instance exists, a new one is created.
     *
     * @return The singleton instance of MedicalHistoryManager.
     */
    public static MedicalHistoryManager getInstance() {
        if (instance == null) {
            instance = new MedicalHistoryManager();
        }
        return instance;
    }


    /**
     * Resets the instance of MedicalHistoryManager.
     * This is useful for testing purposes or when reloading data.
     */
    public static void resetInstance() {
        instance = null;
    }


    /**
     * Registers a new veterinary visit for a specific animal.
     *
     * @param animalID The ID of the animal that received the visit.
     * @param visit The {@code VeterinaryVisit} object containing details of the visit.
     */
    protected void registerNewVisit(int animalID, VeterinaryVisit visit) {
        List<VeterinaryVisit> visits;
        if (visitsHistory.containsKey(animalID)) {
            visits = visitsHistory.get(animalID);
        } else {
            visits = new ArrayList<>(20);
        }
        visits.add(visit);
        visitsHistory.put(animalID, visits);
    }


    /**
     * Retrieves all registered veterinary visits across all animals.
     *
     * @return A list of all registered veterinary visits.
     * @throws NoRegistredVisitsException If no visits have been recorded.
     */
    public List<VeterinaryVisit> getAllVisits() throws NoRegistredVisitsException {
        List<VeterinaryVisit> visits = new ArrayList<>(List.of());

        for (Map.Entry<Integer, List<VeterinaryVisit>> entry : visitsHistory.entrySet()) {
            visits.addAll(entry.getValue());
        }

        if (visits.isEmpty()) {
            throw new NoRegistredVisitsException();
        }
        return visits;
    }
}
