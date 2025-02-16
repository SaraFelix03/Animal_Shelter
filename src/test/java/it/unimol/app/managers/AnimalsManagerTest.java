package it.unimol.app.managers;

import static org.junit.jupiter.api.Assertions.*;

import it.unimol.app.Animal;
import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.exceptions.AnimalAlreadyRegistered;
import it.unimol.app.exceptions.AnimalNotExists;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public class AnimalsManagerTest {
    private static AnimalsManager manager;
    private static final String TEST_SERIALIZATION_FILE = "ShelterTest.sr";

    @BeforeEach
    void setup() throws IOException {
        manager = AnimalsManager.getInstance(TEST_SERIALIZATION_FILE);
    }

    @AfterEach
     void cleanup() {
        File file = new File(TEST_SERIALIZATION_FILE);
        if (file.exists()) {
            file.delete();
        }
        AnimalsManager.resetInstance();
    }

    @Test
    void testAddAnimal() throws IOException, AnimalAlreadyRegistered {
        Animal animal = new Animal("Buddy", "Dog", "2 years", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Friendly");
        manager.addAnimal(animal);
        assertEquals(1, manager.getTotalAnimals(), "Total animals should be 1 after adding an animal");
    }

    @Test
    void testAddAlreadyRegisteredAnimal() throws IOException, AnimalAlreadyRegistered {
        Animal animal = new Animal("Buddy", "Dog", "2 years", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Friendly");
        manager.addAnimal(animal);

        assertThrows(AnimalAlreadyRegistered.class, () -> { manager.addAnimal(animal); });
        assertEquals(1, manager.getTotalAnimals(), "Total animals should be 1 after trying to add " +
                "two times the same animal");
    }

    @Test
    void testRemoveAnimal() throws IOException, AnimalAlreadyRegistered {
        Animal animal = new Animal("Buddy", "Dog", "2 years", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Friendly");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");

        manager.addAnimal(animal);
        manager.addAnimal(animal2);
        assertDoesNotThrow(()->manager.removeAnimalByID(animal.getID()));
        assertEquals(1, manager.getTotalAnimals(), "Total animals should be 1 after adding an animal");
    }

    @Test
    void testRemoveNotExistingAnimal() throws IOException, AnimalAlreadyRegistered {
        Animal animal = new Animal("Giusy", "Snake", "2 years", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Friendly");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");

        manager.addAnimal(animal2);
        assertThrows(AnimalNotExists.class, () -> { manager.removeAnimalByID(animal.getID()); });
    }

    @Test
    void testFindAnimalByID() throws AnimalAlreadyRegistered, IOException, AnimalNotExists {
        Animal animal = new Animal("Charlie", "Parrot", "5 years", LocalDate.now(), HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Talkative");
        manager.addAnimal(animal);
        Animal found = manager.findAnimalByID(animal.getID());
        assertNotNull(found, "Animal should be found by ID");
        assertEquals("Charlie", found.getName(), "Animal name should match");
    }

    @Test
    void testFindNotExistingAnimalByID() throws AnimalAlreadyRegistered, IOException, AnimalNotExists {
        Animal animal = new Animal("Giusy", "Snake", "2 years", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Friendly");
        Animal animal2 = new Animal("Charlie", "Parrot", "5 years", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Talkative");

        manager.addAnimal(animal2);
        assertThrows(AnimalNotExists.class, () -> { manager.findAnimalByID(animal.getID()); }, "It should throw" +
                "an AnimalNotExists Exception because we search for a not-added animal in the system");
    }

    @Test
    void testFindAnimalsByHealthStatus() throws AnimalAlreadyRegistered, IOException, AnimalNotExists {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        List<Animal> criticalAnimals = manager.findAnimalsByHealthStatus(HealthStatus.CRITICAL);
        assertEquals(1, criticalAnimals.size(), "There should be one animal in critical condition");
    }

}
