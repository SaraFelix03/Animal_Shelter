
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
    void testRegisterNewVisit() {
        VeterinaryVisit visit = new VeterinaryVisit(LocalDate.now(), "Dr. Rossi",
                "Influenza", "Antibiotico", 50.0f);

        assertDoesNotThrow(() -> medicalHistoryManager.registerNewVisit(1, visit));
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