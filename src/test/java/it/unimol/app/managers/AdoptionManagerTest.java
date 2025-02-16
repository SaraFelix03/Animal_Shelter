package it.unimol.app.managers;

import static org.junit.jupiter.api.Assertions.*;

import it.unimol.app.enumerations.ContractStatus;
import org.junit.jupiter.api.*;

import it.unimol.app.managers.AdoptionManager;
import it.unimol.app.exceptions.*;
import it.unimol.app.*;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_METHOD) // Assicura una nuova istanza per ogni test
class AdoptionManagerTest {

    private AdoptionManager adoptionManager;

    @BeforeEach
    void setUp() {
        adoptionManager = AdoptionManager.getInstance();
        AdoptionManager.resetInstance();
    }

    @Test
    void testRegisterNewAdopter() {
        Adopter adopter = new Adopter("Mario", "Rossi", "ABC123",
                new Address("Via Roma", "10", "Milano", "20100", "Italia"),
                "123456789", "mario.rossi@example.com");

        assertDoesNotThrow(() -> adoptionManager.registerNewAdopter(adopter));
        assertThrows(AdopterAlreadyRegistered.class, () -> adoptionManager.registerNewAdopter(adopter));
    }

    @Test
    void testRegisterNewAdoption() {
        Adopter adopter = new Adopter("Luigi", "Verdi", "DEF456",
                new Address("Via Milano", "20", "Torino", "10100", "Italia"),
                "987654321", "luigi.verdi@example.com");

        Adoption adoption = new Adoption(adopter.getId(), 1, LocalDate.now(), ContractStatus.SIGNED);

        assertDoesNotThrow(() -> adoptionManager.registerNewAdoption(1, adoption));
        assertThrows(AnimalAlreadyAdoptedException.class, () -> adoptionManager.registerNewAdoption(1, adoption));
    }

    @Test
    void testFindAdoptionInfo() throws Exception {
        Adoption adoption = new Adoption(1, 100, LocalDate.now(), ContractStatus.SIGNED);
        adoptionManager.registerNewAdoption(100, adoption);

        assertEquals(adoption, adoptionManager.findAdoptionInfo(100));
        assertThrows(AnimalNotAdopted.class, () -> adoptionManager.findAdoptionInfo(200));
    }

    @Test
    void testFindAdopterByID() throws Exception {
        Adopter adopter = new Adopter("Anna", "Bianchi", "GHI789",
                new Address("Via Firenze", "30", "Roma", "00100", "Italia"),
                "333111222", "anna.bianchi@example.com");

        adoptionManager.registerNewAdopter(adopter);

        assertEquals(adopter, adoptionManager.findAdopterByID(adopter.getId()));
        assertThrows(AdopterNotExists.class, () -> adoptionManager.findAdopterByID(999));
    }
}
