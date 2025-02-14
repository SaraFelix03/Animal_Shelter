package it.unimol.app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AdoptionManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static AdoptionManager instance;
    private static Map<Integer, Adoption> adoptions;

    private AdoptionManager() {
        adoptions = new HashMap();
    }

    public static AdoptionManager getInstance() {
        if (instance == null) {
            instance = new AdoptionManager();
        }

        return instance;
    }
}
