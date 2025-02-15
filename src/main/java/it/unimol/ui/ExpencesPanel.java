package it.unimol.ui;

import it.unimol.app.VeterinaryVisit;
import it.unimol.app.exceptions.NoRegistredVisitsException;
import it.unimol.app.managers.AnimalsManager;
import it.unimol.app.managers.MedicalHistoryManager;

import java.util.List;

public class ExpencesPanel implements Panel{

    private AnimalsManager animalsManager;
    public ExpencesPanel(AnimalsManager animalsManager){
        this.animalsManager=animalsManager;
    }

    @Override
    public void start() {

        MedicalHistoryManager medicalHistoryManager=animalsManager.getMedicalHistoryManager();
        List<VeterinaryVisit> visits;

        try{
            visits=medicalHistoryManager.getAllVisits();
        }catch(NoRegistredVisitsException noRegistredVisitsException){
            System.out.println("No medical history found");
            return;
        }

        for (VeterinaryVisit visit : visits) {
            System.out.println(" - Date: " + visit.getDateOfVisit() + ", Cost: " + visit.getCost());
        }
    }
}
