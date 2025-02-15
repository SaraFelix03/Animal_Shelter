package it.unimol.app.managers;

import it.unimol.app.Adopter;
import it.unimol.app.Adoption;
import it.unimol.app.exceptions.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AdoptionManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int adopterIdCounter;

    private static AdoptionManager instance;
    private static List<Adopter> adopterList;
    private static Map<Integer, Adoption> adoptions;

    public static AdoptionManager getInstance() {
        if (instance == null) {
            instance = new AdoptionManager();
        }

        return instance;
    }

    public static int getNewAdopterID() {
        adopterIdCounter++;
        return adopterIdCounter;
    }

    public static int getLastAdopterID() {
        return adopterIdCounter;
    }

    protected int getTotalAdoptions(){
        return adoptions.size();
    }

    public void registerNewAdopter(Adopter adopter) throws AdopterAlreadyRegistered {
        if(!adopterList.contains(adopter)) {
            adopterList.add(adopter);
        }
        throw new AdopterAlreadyRegistered();
    }

    public void registerNewAdoption(int animalId, Adoption adoption) throws AnimalAlreadyAdoptedException {
        if(adoptions.containsKey(animalId)){
            throw new AnimalAlreadyAdoptedException();
        }
        adoptions.put(animalId, adoption);
    }

    public Adoption findAdoptionInfo(int animalID) throws AnimalNotAdopted {
        if(!adoptions.containsKey(animalID)){
            throw new AnimalNotAdopted();
        }
        return adoptions.get(animalID);
    }

    public Adopter findAdopterByID(int id) throws AdopterNotExists {
        return adopterList.stream()
                .filter(adopter -> adopter.getId() == id)
                .findFirst()
                .orElseThrow(AdopterNotExists::new);
    }

}
