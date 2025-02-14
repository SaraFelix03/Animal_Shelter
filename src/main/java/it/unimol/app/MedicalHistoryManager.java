package it.unimol.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalHistoryManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static MedicalHistoryManager instance;
    private static Map<Integer, List<VeterinaryVisit>> visitsHistory;

    private MedicalHistoryManager() {
        visitsHistory = new HashMap();
    }

    public static MedicalHistoryManager getInstance() {
        if (instance == null) {
            instance = new MedicalHistoryManager();
        }

        return instance;
    }
}
