package it.unimol.app.managers;

import it.unimol.app.VeterinaryVisit;
import it.unimol.app.exceptions.NoRegistredVisitsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalHistoryManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private static MedicalHistoryManager instance;
    private static Map<Integer, List<VeterinaryVisit>> visitsHistory;

    private MedicalHistoryManager() {
        visitsHistory = new HashMap<Integer, List<VeterinaryVisit>>();
    }

    public static MedicalHistoryManager getInstance() {
        if (instance == null) {
            instance = new MedicalHistoryManager();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

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
