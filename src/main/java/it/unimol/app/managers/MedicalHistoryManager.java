package it.unimol.app.managers;

import it.unimol.app.Animal;
import it.unimol.app.VeterinaryVisit;

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

    protected void registerNewVisit(int animalID, VeterinaryVisit visit) {
        List<VeterinaryVisit> visits;
        if(visitsHistory.containsKey(animalID)){
            visits = visitsHistory.get(animalID);
        }else{
            visits = new ArrayList<>(20);
        }
        visits.add(visit);
        visitsHistory.put(animalID, visits);
    }

}
