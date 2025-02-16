package it.unimol.app.managers;

import static org.junit.jupiter.api.Assertions.*;

import it.unimol.app.Adoption;
import it.unimol.app.Animal;
import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.ContractStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.exceptions.AnimalAlreadyAdoptedException;
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
    void testGetTotalAnimals_Empty() {
        assertEquals(0, manager.getTotalAnimals(), "Total animals should be 0 when no animals are added.");
    }

    @Test
    void testGetTotalAnimals_WithAnimals() throws IOException, AnimalAlreadyRegistered, AnimalNotExists {
        Animal animal1 = new Animal("Charlie", "Dog", "3",
                LocalDate.of(2022, 5, 10), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        Animal animal2 = new Animal("Milo", "Cat", "2",
                LocalDate.of(2023, 1, 15), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        assertEquals(2, manager.getTotalAnimals(),
                "Total animals should be 2 after adding two animals.");
    }

    @Test
    void testGetTotalAdoptions_WithAdoptions() throws IOException, AnimalAlreadyAdoptedException, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Charlie", "Dog", "3",
                LocalDate.of(2022, 5, 10), HealthStatus.EXCELLENT, AdoptionStatus.ADOPTED,
                "Notes");
        Animal animal2 = new Animal("Milo", "Cat", "2",
                LocalDate.of(2023, 1, 15), HealthStatus.EXCELLENT, AdoptionStatus.ADOPTED,
                "Notes");

        Adoption adoption1 = new Adoption(1,1,LocalDate.of(2024, 2, 16),
                ContractStatus.SIGNED);
        Adoption adoption2 = new Adoption(1,2,LocalDate.of(2024, 2, 16),
                ContractStatus.SIGNED);

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        manager.registerAdoption(animal1, adoption1);
        manager.registerAdoption(animal2, adoption2);

        assertEquals(2, manager.getTotalAdoptions(),
                "Total adoptions should be 2 after registering two adoptions.");
    }

    @Test
    void testGetTotalAnimalsWaitingForAdoption_NoWaitingAnimals() {
        assertEquals(0, manager.getTotalAnimalsWaitingForAdoption(), "Total animals waiting should be 0 when no animals are added.");
    }

    @Test
    void testGetTotalAnimalsWaitingForAdoption_WithWaitingAnimals() throws IOException, AnimalAlreadyAdoptedException, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Charlie", "Dog", "3",
                LocalDate.of(2022, 5, 10), HealthStatus.EXCELLENT, AdoptionStatus.WAITING,
                "Notes");
        Animal animal2 = new Animal("Milo", "Cat", "2",
                LocalDate.of(2023, 1, 15), HealthStatus.EXCELLENT, AdoptionStatus.WAITING,
                "Notes");

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        assertEquals(2, manager.getTotalAnimalsWaitingForAdoption(), "Total animals waiting should be 2 when two animals are waiting for adoption.");
    }

    @Test
    void testGetTotalAnimalsWaitingForAdoption_WithMixedStatuses() throws IOException, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Bella", "Rabbit", "1",
                LocalDate.of(2021, 8, 20), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        Animal animal2 = new Animal("Luna", "Cat", "4",
                LocalDate.of(2023, 1, 15), HealthStatus.GOOD, AdoptionStatus.WAITING,
                "Notes");

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        assertEquals(1, manager.getTotalAnimalsWaitingForAdoption(), "Total animals waiting should be 1 when only one animal is waiting for adoption.");
    }

    @Test
    void testGetTotalAnimalsCriticalCondition_NoCriticalAnimals() {
        assertEquals(0, manager.getTotalAnimalsCriticalCondition(), "Total critical condition animals should be 0 when no animals are added.");
    }

    @Test
    void testGetTotalAnimalsCriticalCondition_WithCriticalAnimals() throws IOException, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Max", "Dog", "5", LocalDate.of(2022, 7, 10), HealthStatus.CRITICAL, AdoptionStatus.AVAILABLE, "Severe illness");
        Animal animal2 = new Animal("Whiskers", "Cat", "3", LocalDate.of(2023, 2, 1), HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Serious injury");

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        assertEquals(2, manager.getTotalAnimalsCriticalCondition(), "Total critical condition animals should be 2 when two animals are in critical condition.");
    }


    @Test
    void testGetTotalAnimalsCriticalCondition_WithMixedHealthStatuses() throws IOException, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Rocky", "Dog", "2",
                LocalDate.of(2021, 9, 15), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Normal checkup");
        Animal animal2 = new Animal("Luna", "Cat", "4",
                LocalDate.of(2023, 3, 20), HealthStatus.CRITICAL, AdoptionStatus.AVAILABLE,
                "Emergency case");

        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        assertEquals(1, manager.getTotalAnimalsCriticalCondition(),
                "Total critical condition animals should be 1 when only one animal is in critical condition.");
    }

    @Test
    void testLoadManager() throws IOException {
        AnimalsManager.AnimalsManagerInitialize();
        assertNotNull(manager);
    }

    @Test
    void testSaveManager_FileCreated() throws IOException, AnimalAlreadyRegistered {

        Animal animal = new Animal("Charlie", "Dog", "3",
                LocalDate.of(2022, 5, 10), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");

        manager.addAnimal(animal);

        File file = new File(TEST_SERIALIZATION_FILE);
        assertTrue(file.exists(), "Serialized file should be created after saveManager() execution.");
        assertTrue(file.length() > 0, "Serialized file should contain data.");
    }

    @Test
    void testLoadManager_FileDoesNotExist() throws IOException {
        AnimalsManager manager = AnimalsManager.loadManager();
        assertNotNull(manager, "A new instance should be created if the file does not exist.");
    }

    @Test
    void testLoadManager_FileExistsWithValidData() throws IOException, AnimalAlreadyRegistered {
        AnimalsManager manager = AnimalsManager.getInstance(TEST_SERIALIZATION_FILE);
        Animal animal = new Animal("Buddy", "Dog", "5",
                LocalDate.of(2023, 1, 1), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        manager.addAnimal(animal);

        AnimalsManager loadedManager = AnimalsManager.loadManager();
        assertNotNull(loadedManager, "Loaded instance should not be null.");
        assertEquals(1, loadedManager.getTotalAnimals(), "Loaded instance should contain the saved data.");
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
    void testFindAnimalsBySpecies_NoAnimals() {
        assertThrows(AnimalNotExists.class,
                () -> manager.findAnimalsBySpecies("Dog"),
                "Should throw AnimalNotExists when no animals are present.");
    }

    @Test
    void testFindAnimalsBySpecies_WithMatchingSpecies() throws IOException, AnimalAlreadyRegistered, AnimalNotExists {
        Animal animal1 = new Animal("Buddy", "Dog", "5",
                LocalDate.of(2023, 1, 1), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        Animal animal2 = new Animal("Charlie", "Dog", "4",
                LocalDate.of(2022, 6, 10), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        List<Animal> foundAnimals = manager.findAnimalsBySpecies("Dog");

        assertFalse(foundAnimals.isEmpty(), "The method should return a list of found animals.");
        assertEquals(2, foundAnimals.size(), "There should be 2 dogs found.");
        assertEquals("Dog", foundAnimals.get(0).getSpecies(), "The species should match.");
    }

    @Test
    void testFindAnimalsBySpecies_NoMatchingSpecies() throws IOException, AnimalAlreadyRegistered{
        Animal animal = new Animal("Whiskers", "Cat", "3",
                LocalDate.of(2023, 3, 20), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        manager.addAnimal(animal);

        assertThrows(AnimalNotExists.class,
                () -> manager.findAnimalsBySpecies("Dog"),
                "Should throw AnimalNotExists when no animals match the species.");
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

    @Test
    void testFindAnimalsByAdoptionStatus_NoAnimals() {
        assertThrows(AnimalNotExists.class,
                () -> manager.findAnimalsByAdoptionStatus(AdoptionStatus.AVAILABLE),
                "Should throw AnimalNotExists when no animals are present.");
    }


    @Test
    void testFindAnimalsByAdoptionStatus_WithMatchingStatus() throws IOException, AnimalNotExists, AnimalAlreadyRegistered {
        Animal animal1 = new Animal("Buddy", "Dog", "5",
                LocalDate.of(2023, 1, 1), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        Animal animal2 = new Animal("Charlie", "Dog", "4",
                LocalDate.of(2022, 6, 10), HealthStatus.GOOD, AdoptionStatus.AVAILABLE,
                "Notes");
        manager.addAnimal(animal1);
        manager.addAnimal(animal2);

        List<Animal> foundAnimals = manager.findAnimalsByAdoptionStatus(AdoptionStatus.AVAILABLE);

        assertFalse(foundAnimals.isEmpty(), "The method should return a list of found animals.");
        assertEquals(2, foundAnimals.size(), "There should be 2 available animals.");
        assertEquals(AdoptionStatus.AVAILABLE, foundAnimals.get(0).getAdoptionStatus(), "The adoption status should match.");
    }

    @Test
    void testFindAnimalsByAdoptionStatus_NoMatchingStatus() throws IOException, AnimalAlreadyRegistered {
        Animal animal = new Animal("Whiskers", "Cat", "3",
                LocalDate.of(2023, 3, 20), HealthStatus.GOOD, AdoptionStatus.ADOPTED,
                "Notes");
        manager.addAnimal(animal);

        assertThrows(AnimalNotExists.class,
                () ->  manager.findAnimalsByAdoptionStatus(AdoptionStatus.WAITING),
                "Should throw AnimalNotExists when no animals match the adoption status.");
    }

    @Test
    void testEquals_SameObject() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        assertTrue(animal1.equals(animal1), "An object should be equal to itself");
    }

    @Test
    void testEquals_EqualObjects() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        assertEquals(animal1, animal2, "Two animals with the same ID should be equal");
    }

    @Test
    void testEquals_DifferentObjects() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertNotEquals(animal1, animal2, "Different animals should not be equal");
    }

    @Test
    void testEquals_NullObject() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        assertNotEquals(animal1, null, "An object should not be equal to null");
    }

    @Test
    void testEquals_DifferentClass() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        String notAnAnimal = "Not an Animal";
        assertNotEquals(animal1, notAnAnimal, "An object should not be equal to an object of a different class");
    }

    @Test
    void testEquals_NullComparison() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        assertFalse(animal1.equals(null), "An object should not be equal to null");
    }

    @Test
    void testEquals_DifferentName() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different names should not be equal");
    }

    @Test
    void testEquals_DifferentSpecies() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different species should not be equal");
    }

    @Test
    void testEquals_DifferentAge() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different ages should not be equal");
    }

    @Test
    void testEquals_DifferentAdmissionDate() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different admission dates should not be equal");
    }

    @Test
    void testEquals_DifferentHealthStatus() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different health statuses should not be equal");
    }

    @Test
    void testEquals_DifferentAdoptionStatus() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertFalse(animal1.equals(animal2), "Animals with different adoption statuses should not be equal");
    }

    @Test
    void testEquals_IdenticalAnimals() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        assertTrue(animal1.equals(animal2), "Identical animals should be equal");
    }

    @Test
    void testHashCode_DifferentObjects() {
        Animal animal1 = new Animal("Luna", "Rabbit", "1 year", LocalDate.now(),
                HealthStatus.CRITICAL, AdoptionStatus.WAITING, "Needs urgent care");
        Animal animal2 = new Animal("Rocky", "Hamster", "6 months", LocalDate.now(),
                HealthStatus.GOOD, AdoptionStatus.AVAILABLE, "Active");
        assertNotEquals(animal1.hashCode(), animal2.hashCode(), "Different objects should have different hashCodes");
    }

}
