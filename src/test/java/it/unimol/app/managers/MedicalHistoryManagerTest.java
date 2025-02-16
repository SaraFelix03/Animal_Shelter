
package it.unimol.app.managers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.unimol.app.VeterinaryVisit;
import it.unimol.app.exceptions.NoRegistredVisitsException;

import java.time.LocalDate;
import java.util.List;

class MedicalHistoryManagerTest {

    private MedicalHistoryManager medicalHistoryManager;

    @BeforeEach
    void setUp() {
        medicalHistoryManager = MedicalHistoryManager.getInstance();
        MedicalHistoryManager.resetInstance();
    }

    @Test
    void testRegisterNewVisit_ExistingAnimal() throws NoRegistredVisitsException{
        int animalID = 1;
        VeterinaryVisit visit1 = new VeterinaryVisit(LocalDate.now(), "Dr. Bianchi",
                "Otite", "Gocce auricolari", 40.0f);
        medicalHistoryManager.registerNewVisit(animalID, visit1);

        VeterinaryVisit visit2 = new VeterinaryVisit(LocalDate.now().minusDays(10), "Dr. Verdi",
                "Frattura", "Fasciatura", 100.0f);
        medicalHistoryManager.registerNewVisit(animalID, visit2);

        List<VeterinaryVisit> visits = medicalHistoryManager.getAllVisits();
        assertEquals(2, visits.size(), "Both visits should be registered under the same animalID");
    }


    @Test
    void testGetAllVisitsSuccess() throws Exception {
        VeterinaryVisit visit1 = new VeterinaryVisit(LocalDate.now(), "Dr. Bianchi",
                "Otite", "Gocce auricolari", 40.0f);
        VeterinaryVisit visit2 = new VeterinaryVisit(LocalDate.now().minusDays(10), "Dr. Verdi",
                "Frattura", "Fasciatura", 100.0f);

        medicalHistoryManager.registerNewVisit(1, visit1);
        medicalHistoryManager.registerNewVisit(2, visit2);

        List<VeterinaryVisit> visits = medicalHistoryManager.getAllVisits();

        assertEquals(2, visits.size());
        assertTrue(visits.contains(visit1));
        assertTrue(visits.contains(visit2));
    }

    @Test
    void testGetAllVisitsFailure() {
        assertThrows(NoRegistredVisitsException.class, () -> medicalHistoryManager.getAllVisits());
    }

}