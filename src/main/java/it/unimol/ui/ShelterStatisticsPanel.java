package it.unimol.ui;

import it.unimol.app.managers.AnimalsManager;

public class ShelterStatisticsPanel implements Panel{

    private AnimalsManager animalsManager;

    public ShelterStatisticsPanel(AnimalsManager animalsManager){
        this.animalsManager=animalsManager;
    }

    @Override
    public void start() {
        System.out.println("**SHELTER STATISTICS**");
        System.out.println("- Total number of animals:" + animalsManager.getTotalAnimals());
        System.out.println("- Total number of adoptions:" + animalsManager.getTotalAdoptions());
        System.out.println("- Total number of animals waiting for adoption:"
                + animalsManager.getTotalAnimalsWaitingForAdoption());
        System.out.println("- Total number of animals in critical condition:"
                + animalsManager.getTotalAnimalsCriticalCondition());
    }
}
