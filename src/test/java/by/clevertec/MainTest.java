package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void task1TestWithNormalParameters() {
        List<Animal> animals = Util.getAnimals();

        List<Animal> zooNumber = Main.task1(animals, 3);

        assertNotNull(zooNumber, "List of animals should not be null with existing animals list");
        assertFalse(zooNumber.isEmpty(), "List of animals should not be empty with existing animals list" +
                "and zooNumber in existing range of animals");
        assertTrue(zooNumber.get(0).getAge() < zooNumber.get(zooNumber.size() - 1).getAge(),
                "Animals in zoo should be in ASC order by age");
    }

    @Test
    void task1TestWithNegativeZooNumberParameters() {
        List<Animal> animals = Util.getAnimals();

        List<Animal> zooNumber = Main.task1(animals, -1);

        assertNotNull(zooNumber, "List of animals should not be null with negative zooNumber");
        assertTrue(zooNumber.isEmpty(), "List of animals should be empty with negative zooNumber");
    }

    @Test
    void task1TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        List<Animal> zooNumber = Main.task1(animals, 1);

        assertNotNull(zooNumber, "List of animals should not be null with empty animal list");
        assertTrue(zooNumber.isEmpty(), "List of animals should be empty with empty animal list");
    }

    @Test
    void task2TestWithNormalParameters() {
        List<Animal> animals = Util.getAnimals();

        String japaneseBreads = Main.task2(animals);

        boolean allFemaleJapaneseAnimalsWithUpperCaseBreads = animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()) && "Female".equals(animal.getGender()))
                .allMatch(animal -> animal.getBread().matches("([A-Z\\W])+"));

        assertNotNull(japaneseBreads, "japaneseBreads string should not be null with existing animal list");
        assertFalse(japaneseBreads.isEmpty(), "japaneseBreads string should not be empty with " +
                "existing animal list");
        assertTrue(allFemaleJapaneseAnimalsWithUpperCaseBreads, "All Animals from Japanese should have " +
                "bread with uppercase");
    }

    @Test
    void task2TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        String japaneseBreads = Main.task2(animals);

        assertNotNull(japaneseBreads, "japaneseBreads string should not be null with empty list");
        assertTrue(japaneseBreads.isEmpty(), "japaneseBreads string should be empty with empty list");
    }

    @Test
    void task3TestWithNormalParameters() {
        List<Animal> animals = Util.getAnimals();
        List<String> origins = animals.stream()
                .filter(animal -> animal.getAge() > 30 && animal.getOrigin().toUpperCase().startsWith("A"))
                .map(Animal::getOrigin)
                .toList();

        Set<String> uniqAnimalsOrigin = Main.task3(animals);

        assertNotNull(origins, "Origins should not be null");
        assertNotNull(uniqAnimalsOrigin, "Origins should not be null");

        assertTrue(origins.size() > uniqAnimalsOrigin.size(), "Origins should be more " +
                "than one uniqAnimalsOrigin with existing Animal list");
        assertTrue(uniqAnimalsOrigin.containsAll(origins), "uniqAnimalsOrigin should contain all" +
                " origins from origins list");
    }

    @Test
    void task3TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Set<String> uniqAnimalsOrigin = Main.task3(animals);

        assertNotNull(uniqAnimalsOrigin, "uniqAnimalsOrigin should not be null with empty animals list");
        assertTrue(uniqAnimalsOrigin.isEmpty(), "uniqAnimalsOrigin should be empty with empty animals list");
    }

    @Test
    void task4TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();
        Long countFemaleGender = animals.stream()
                .filter(animal -> "Female".equalsIgnoreCase(animal.getGender()))
                .count();

        Long testedCountFemaleGender = Main.task4(animals);

        assertNotNull(countFemaleGender, "countFemaleGender should not be null with existing animal list");
        assertNotNull(testedCountFemaleGender, "testedCountFemaleGender should not be null with existing " +
                "animal list");

        assertEquals(countFemaleGender, testedCountFemaleGender, "countFemaleGender and testedCountFemaleGender" +
                " should be the same");
    }

    @Test
    void task4TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Long testedCountFemaleGender = Main.task4(animals);

        assertNotNull(testedCountFemaleGender, "testedCountFemaleGender should not be null" +
                " with empty animals list");
        assertEquals(0, testedCountFemaleGender, "testedCountFemaleGender should be " +
                "zero with empty animals list");
    }

    @Test
    void task5TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();

        boolean containOriginHungarian = Main.task5(animals);

        assertTrue(containOriginHungarian, "Method should return true if origin is Hungarian and " +
                "animal age in range of 20-30");
    }

    @Test
    void task5TestWithEmptyList() {
        List<Animal> animals = Collections.emptyList();

        boolean containOriginHungarian = Main.task5(animals);

        assertFalse(containOriginHungarian, "Empty list should return false because no animal from Hungarian");
    }

    @Test
    void task6TestWithOnlyMaleAnimals() {
        List<Animal> animals = Util.getAnimals();
        animals = animals.stream()
                .filter(animal -> Main.Gender.MALE.toString().equalsIgnoreCase(animal.getGender()))
                .toList();

        boolean isAllGenderMale = Main.task6(animals, Main.Gender.MALE);

        assertTrue(isAllGenderMale, "isAllGenderMale should return true because we pass only Male gender animals");
    }

    @Test
    void task6TestWithRandomAnimalsGender() {
        List<Animal> animals = Util.getAnimals();

        boolean isAllGenderFemale = Main.task6(animals, Main.Gender.FEMALE);

        assertFalse(isAllGenderFemale, "isAllGenderFemale should return false because we pass diff gender animals");
    }

    @Test
    void task6TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        boolean isAllGenderFemale = Main.task6(animals, Main.Gender.FEMALE);

        assertFalse(isAllGenderFemale, "isAllGenderFemale should return false with empty animals list");
    }

    @Test
    void task7TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();

        boolean notContainOriginOceania = Main.task7(animals);

        assertTrue(notContainOriginOceania, "Existing animal list not contain animals with origin Oceania");
    }

    @Test
    void task7TestWithOceaniaOrigin() {
        List<Animal> animals = Util.getAnimals();
        animals.get(0).setOrigin("Oceania");

        boolean notContainOriginOceania = Main.task7(animals);

        assertFalse(notContainOriginOceania, "Should return false if animal list contains " +
                "one or more animals with origin Oceania");
    }

    @Test
    void task7TestWithEmptyList() {
        List<Animal> animals = Collections.emptyList();

        boolean notContainOriginOceania = Main.task7(animals);

        assertTrue(notContainOriginOceania, "Should return true with empty list animals");
    }

    @Test
    void task8TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();

        Integer maxAge = Main.task8(animals);
        Animal oldestAnimal = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(10)
                .max(Comparator.comparing(Animal::getAge))
                .orElse(null);


        assertNotNull(oldestAnimal, "Animal with max age should not be null with existing animal list");
        assertTrue(maxAge > -1, "Max age should be greater than zero with existing animals list");
        assertEquals(maxAge, oldestAnimal.getAge(), "Max age should be equal to oldest animal");
    }

    @Test
    void task8TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Integer maxAge = Main.task8(animals);

        assertEquals(-1, maxAge, "Max age should be -1 with empty animals list");
    }

    @Test
    void task9TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();
        Integer actualMinBreadLength = animals.stream()
                .min(Comparator.comparingInt(animal -> animal.getBread().length()))
                .map(animal -> animal.getBread().length())
                .orElse(-1);

        Integer minBreadLength = Main.task9(animals);

        assertTrue(actualMinBreadLength > -1, "actualMinBreadLength should be greater than -1 " +
                "with existing animal list");
        assertTrue(minBreadLength > -1, "minBreadLength should be greater than -1 " +
                "with existing animal list");
        assertEquals(actualMinBreadLength, minBreadLength, "minBreadLength should be the same as " +
                "actualMinBreadLength");
    }

    @Test
    void task9TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Integer minBreadLength = Main.task9(animals);

        assertEquals(0, minBreadLength, "minBreadLength should be 0 with empty animals list");
    }

    @Test
    void task10TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();
        Integer actualSumAge = animals.stream()
                .reduce(0, (acc, animal) -> acc + animal.getAge(), Integer::sum);

        Integer sumAge = Main.task10(animals);

        assertTrue(actualSumAge > 0, "actualSumAge should be greater than zero with existing animals list");
        assertTrue(sumAge > 0, "sumAge should be greater than zero with existing animals list");
        assertEquals(actualSumAge, sumAge, "sum age should be the same as actualSumAge");
    }

    @Test
    void task10TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Integer sumAge = Main.task10(animals);

        assertEquals(0, sumAge, "sum age should be 0 with empty animals list");
    }

    @Test
    void task11TestWithNormalParam() {
        List<Animal> animals = Util.getAnimals();
        Double actualAverageAge = animals.stream()
                .filter(animal -> "Indonesian".equalsIgnoreCase(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0);

        Double averageAge = Main.task11(animals);

        assertNotEquals(0, actualAverageAge, "Actual average age should be more than zero " +
                "with existing animals list");
        assertNotEquals(0, averageAge, "average age should be more than zero " +
                "with existing animals list");
        assertEquals(actualAverageAge, averageAge, "average age should be the same as actualAverageAge");
    }

    @Test
    void task11TestWithEmptyAnimalList() {
        List<Animal> animals = Collections.emptyList();

        Double sumAge = Main.task11(animals);

        assertEquals(0, sumAge, "sum age should be 0 with empty animals list");
    }
}
