package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.CountryCarBill;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.PersonBuilding;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public enum Gender {MALE, FEMALE}

    public static void main(String[] args) {
        //Task 1: Zoo number - 3, start from 0
        System.out.println(task1(Util.getAnimals(), 3));
        //Task 2:
        System.out.println(task2(Util.getAnimals()));
        //Task 3:
        System.out.println(task3(Util.getAnimals()));
        //Task 4:
        System.out.println(task4(Util.getAnimals()));
        //Task 5:
        System.out.println(task5(Util.getAnimals()));
        //Task 6:
        System.out.println(task6(Util.getAnimals(), Gender.MALE));
        //Task 7:
        System.out.println(task7(Util.getAnimals()));
        //Task 8: The number of animals decreased proportionally, from 100 to 10
        System.out.println(task8(Util.getAnimals()));
        //Task 9:
        System.out.println(task9(Util.getAnimals()));
        //Task 10:
        System.out.println(task10(Util.getAnimals()));
        //Task 11:
        System.out.println(task11(Util.getAnimals()));
        //Task 12: The number of person decreased proportionally, from 200 to 20
        System.out.println(task12(Util.getPersons()));
        //Task 13: The number of person decreased proportionally, from 500 to 50
        System.out.println(task13(Util.getHouses()));
        //Task 14:
        System.out.println(task14(Util.getCars())); //total company profit
        //Task 15:
        System.out.println(task15(Util.getFlowers()));
        //Task 16:
        System.out.println(task16(Util.getStudents()));
        //Task 17:
        System.out.println(task17(Util.getStudents()));
        //Task 18:
        System.out.println(task18(Util.getStudents()));
        //Task 19:
        System.out.println(task19(Util.getStudents(), Util.getExaminations(), "C-3"));
        //Task 20:
        System.out.println(task20(Util.getStudents(), Util.getExaminations()));
        //Task 21:
        System.out.println(task21(Util.getStudents()));
        //Task 22:
        System.out.println(task22(Util.getStudents()));
    }

    public static List<Animal> task1(List<Animal> animals, int zooNumber) {
        final AtomicInteger counter = new AtomicInteger();
        final int partitionSize = 7;

        Map<Integer, List<Animal>> zoos = animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.groupingBy(animal -> counter.getAndIncrement() / partitionSize));

        return zoos.get(zooNumber) == null ? Collections.emptyList() : zoos.get(zooNumber);
    }

    public static String task2(List<Animal> animals) {
        String originName = "Japanese";
        String gender = "Female";
        String separator = ";";

        return animals.stream()
                .filter(animal -> originName.equalsIgnoreCase(animal.getOrigin())
                        && gender.equalsIgnoreCase(animal.getGender()))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .collect(
                        StringBuilder::new,
                        (sb, animal) -> sb.append(animal.getBread()).append(separator),
                        StringBuilder::append
                )
                .toString();
    }

    public static Set<String> task3(List<Animal> animals) {
        int minAnimalAge = 30;
        String originStartWith = "A";

        return animals.stream()
                .filter(animal -> animal.getAge() > minAnimalAge &&
                        animal.getOrigin().toUpperCase().startsWith(originStartWith))
                .map(Animal::getOrigin)
                .collect(Collectors.toSet());
    }

    public static Long task4(List<Animal> animals) {
        String gender = "Female";

        return animals.stream()
                .filter(animal -> gender.equalsIgnoreCase(animal.getGender()))
                .count();
    }

    public static boolean task5(List<Animal> animals) {
        int minAnimalAge = 20;
        int maxAnimalAge = 30;
        String originToFound = "Hungarian";

        return animals.stream()
                .anyMatch(animal -> animal.getAge() >= minAnimalAge && animal.getAge() <= maxAnimalAge &&
                        originToFound.equalsIgnoreCase(animal.getOrigin()));
    }

    public static boolean task6(List<Animal> animals, Gender gender) {
        return !animals.isEmpty() && animals.stream()
                .allMatch(animal -> gender.toString().equalsIgnoreCase(animal.getGender()));
    }

    public static boolean task7(List<Animal> animals) {
        String originToNotFound = "Oceania";

        return animals.stream()
                .noneMatch(animal -> originToNotFound.equalsIgnoreCase(animal.getOrigin()));
    }

    public static Integer task8(List<Animal> animals) {
        int limitOfAnimals = 10;

        return animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(limitOfAnimals)
                .map(Animal::getAge)
                .max(Integer::compareTo)
                .orElse(-1);
    }

    public static Integer task9(List<Animal> animals) {
        return animals.stream()
                .map(animal -> animal.getBread().toCharArray())
                .min(Comparator.comparingInt(charArr -> charArr.length))
                .map(chars -> chars.length)
                .orElse(0);
    }

    public static Integer task10(List<Animal> animals) {
        return animals.stream()
                .reduce(0, (integer, animal) -> integer + animal.getAge(), Integer::sum);
    }

    public static Double task11(List<Animal> animals) {
        String originToCount = "Indonesian";

        return animals.stream()
                .filter(animal -> originToCount.equalsIgnoreCase(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(0);
    }

    public static List<Person> task12(List<Person> persons) {
        return persons.stream()
                .filter(Main::checkIfMaleAndAgeFrom18To27)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(20)
                .toList();
    }

    private static boolean checkIfMaleAndAgeFrom18To27(Person person) {
        int minAge = 18;
        int maxAge = 27;
        String gender = "Male";

        long personAge = ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now());

        return personAge >= minAge && personAge <= maxAge && gender.equalsIgnoreCase(person.getGender());
    }

    public static List<PersonBuilding> task13(List<House> houses) {
        return houses.stream()
                .map(Main::convertHouseToPersonBuilding)
                .flatMap(Collection::stream)
                .sorted(
                        Comparator.comparing(Main::compareIfHospital).thenComparing(Main::compareIfYoungOrRetiree)
                )
                .limit(50)
                .toList();
    }

    private static int compareIfHospital(PersonBuilding personBuilding) {
        return "Hospital".equalsIgnoreCase(personBuilding.getBuildingType()) ? -1 : 1;
    }

    private static int compareIfYoungOrRetiree(PersonBuilding personBuilding) {
        int youngUntilAge = 18;
        int retireeFromAge = 63;

        return (personBuilding.getAge() <= youngUntilAge || personBuilding.getAge() >= retireeFromAge) ? -1 : 1;
    }

    private static List<PersonBuilding> convertHouseToPersonBuilding(House house) {
        return house.getPersonList().stream()
                .map(person -> new PersonBuilding(house.getBuildingType(), getPersonAge(person), person))
                .toList();
    }

    private static long getPersonAge(Person person) {
        return ChronoUnit.YEARS.between(person.getDateOfBirth(), LocalDate.now());
    }

    public static double task14(List<Car> cars) {
        Map<String, List<Car>> countryCars = cars.stream()
                .collect(
                        HashMap::new,
                        Main::sortCarsByCountry,
                        HashMap::putAll
                );

        return countryCars.keySet().stream()
                .map(country -> calculateCountryCarBill(country, countryCars.get(country)))
                .peek(System.out::println) //Total price and total tax per country
                .mapToDouble(countryCarBill -> countryCarBill.getTotalPrice() - countryCarBill.getTotalTax())
                .sum(); //Total company profit
    }

    private static void sortCarsByCountry(Map<String, List<Car>> countyCars, Car car) {
        List<String> countryList = Stream.of("1. Turkmenistan", "2. Uzbekistan", "3. Kazakhstan",
                        "4. Kyrgyzstan", "5. Russia", "6. Mongolia")
                .toList();
        Set<String> uzbekistanModeMakelSet = Set.of("BMW", "LEXUS", "CHRYSLER", "TOYOTA");
        Set<String> kazakhstanModelMakeSet = Set.of("GMC", "DODGE");
        Set<String> kyrgyzstanModelList = Set.of("CIVIC", "CHEROKEE");
        Set<String> russiaModelColorList = Set.of("YELLOW", "RED", "GREEN", "BLUE");

        //1. Turkmenistan
        if ("Jaguar".equalsIgnoreCase(car.getCarMake()) || "White".equalsIgnoreCase(car.getColor())) {
            putCarInCountryCarMap(countyCars, car, countryList.get(0));
            return;
        }

        //2. Uzbekistan
        if (car.getMass() < 1500 || uzbekistanModeMakelSet.contains(car.getCarMake().toUpperCase())) {
            putCarInCountryCarMap(countyCars, car, countryList.get(1));
            return;
        }

        //3. Kazakhstan
        if (("Black".equalsIgnoreCase(car.getColor()) && car.getMass() > 4000) ||
                kazakhstanModelMakeSet.contains(car.getCarMake().toUpperCase())) {

            putCarInCountryCarMap(countyCars, car, countryList.get(2));
            return;
        }

        //4. Kyrgyzstan
        if (car.getReleaseYear() < 1982 || kyrgyzstanModelList.contains(car.getCarModel().toUpperCase())) {
            putCarInCountryCarMap(countyCars, car, countryList.get(3));
            return;
        }

        //5. Russia
        if (car.getPrice() > 40000 || !russiaModelColorList.contains(car.getColor().toUpperCase())) {
            putCarInCountryCarMap(countyCars, car, countryList.get(4));
            return;
        }

        //6. Mongolia
        if (car.getVin().contains("56")) {
            putCarInCountryCarMap(countyCars, car, countryList.get(5));
        }
    }

    private static void putCarInCountryCarMap(Map<String, List<Car>> carMap, Car car, String country) {
        List<Car> carList = carMap.get(country);

        if (null == carList) {
            carMap.put(country, new ArrayList<>() {{
                add(car);
            }});
        } else {
            carList.add(car);
        }
    }

    private static CountryCarBill calculateCountryCarBill(String country, List<Car> cars) {
        double dollarTaxPerTon = 7.14;

        CountryCarBill countryCarBill = new CountryCarBill();
        countryCarBill.setCountry(country);
        countryCarBill.setTotalPrice(cars.stream().mapToDouble(Car::getPrice).sum());
        countryCarBill.setTotalMass(cars.stream().mapToDouble(Car::getMass).sum());
        countryCarBill.setTotalTax((countryCarBill.getTotalMass() / 1000) * dollarTaxPerTon);

        return countryCarBill;
    }

    public static double task15(List<Flower> flowers) {
        return flowers.stream()
                .sorted(
                        Comparator.comparing(Flower::getOrigin).reversed()
                                .thenComparing(Flower::getPrice)
                                .thenComparing(Comparator.comparingDouble(Flower::getWaterConsumptionPerDay).reversed())
                )
                .filter(Main::flowerFilter)
                .mapToDouble(Main::countFlowerMaintainPriceFor5Years)
                .sum();
    }

    private static boolean flowerFilter(Flower flower) {
        Set<String> allowedVaseMaterial = Set.of("Glass", "Aluminum", "Steel");

        return flower.getCommonName().toUpperCase().matches("[C-S]+") && flower.isShadePreferred() &&
                flower.getFlowerVaseMaterial().stream().anyMatch(allowedVaseMaterial::contains);
    }

    private static double countFlowerMaintainPriceFor5Years(Flower flower) {
        double priceForCubicMeterOfWater = 1.39;
        int daysInFiveYears = 1826;

        return flower.getPrice() +
                ((flower.getWaterConsumptionPerDay() * daysInFiveYears) / 1000) * priceForCubicMeterOfWater;
    }

    public static List<Student> task16(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .toList();
    }

    public static List<String> task17(List<Student> students) {
        return students.stream()
                .map(Student::getGroup)
                .distinct()
                .toList();
    }

    public static List<String> task18(List<Student> students) {
        Map<String, List<Student>> facultyStudents = students.stream()
                .collect(
                        HashMap::new,
                        Main::sortStudentsByFaculty,
                        HashMap::putAll
                );

        return facultyStudents.keySet().stream()
                .map(group -> getAverageGroupAge(facultyStudents.get(group), group))
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    private static void sortStudentsByFaculty(HashMap<String, List<Student>> groupStudents, Student student) {
        List<Student> studentList = groupStudents.get(student.getFaculty());

        if (null == studentList) {
            groupStudents.put(student.getFaculty(), new ArrayList<>() {{add(student);}});
        } else {
            studentList.add(student);
        }
    }

    private static String getAverageGroupAge(List<Student> students, String group) {
        return students.stream().mapToDouble(Student::getAge).average().orElse(0) + " : " + group;
    }

    public static List<Student> task19(List<Student> students, List<Examination> examinations, String group) {
        return students.stream()
                .filter(student -> student.getGroup().equalsIgnoreCase(group) &&
                        isPassedExamStudents(examinations, student))
                .toList();
    }

    private static boolean isPassedExamStudents(List<Examination> examinations, Student student) {
        return examinations.stream()
                .anyMatch(exam -> exam.getStudentId() == student.getId() && exam.getExam3() > 4);
    }

    public static String task20(List<Student> students, List<Examination> examinations) {
        Map<String, List<Student>> facultyStudents = students.stream()
                .collect(
                        HashMap::new,
                        Main::sortStudentsByFaculty,
                        HashMap::putAll
                );

        return facultyStudents.keySet().stream()
                .map(faculty -> getStudentsAverageScore(facultyStudents.get(faculty), examinations) + ":" + faculty)
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .map(str -> str.substring(str.indexOf(":") + 1))
                .orElse("");
    }

    private static Double getStudentsAverageScore(List<Student> students, List<Examination> examinations) {
        return students.stream()
                .mapToInt(student ->
                        examinations.stream()
                                .filter(exam -> exam.getStudentId() == student.getId())
                                .findFirst()
                                .map(Examination::getExam1)
                                .orElse(0)
                )
                .average()
                .orElse(0);
    }

    public static List<String> task21(List<Student> students) {
        Map<String, List<Student>> groupStudents = students.stream()
                .collect(
                        HashMap::new,
                        Main::sortStudentsByGroup,
                        HashMap::putAll
                );

        return groupStudents.keySet().stream()
                .map(group -> group + " : " + groupStudents.get(group).size())
                .toList();
    }

    private static void sortStudentsByGroup(HashMap<String, List<Student>> groupStudents, Student student) {
        List<Student> studentList = groupStudents.get(student.getGroup());

        if (null == studentList) {
            groupStudents.put(student.getGroup(), new ArrayList<>() {{add(student);}});
        } else {
            studentList.add(student);
        }
    }

    public static List<String> task22(List<Student> students) {
        Map<String, List<Student>> facultyStudents = students.stream()
                .collect(
                        HashMap::new,
                        Main::sortStudentsByFaculty,
                        HashMap::putAll
                );

        return facultyStudents.keySet().stream()
                .map(faculty -> faculty + " : " +
                        facultyStudents.get(faculty).stream()
                                .mapToInt(Student::getAge)
                                .min()
                                .orElse(0)
                )
                .toList();
    }
}
