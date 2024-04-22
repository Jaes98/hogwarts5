package dk.kea.dat3js.hogwarts5.teachers;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void getFullNameWithMiddleName() {
        //arrange
        Teacher teacher = new Teacher("Severus", "Prince", "Snape", null, "Potions" , LocalDate.now());

        //act
        String fullName = teacher.getFullName();

        //assert
        assertEquals("Severus Prince Snape", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        //arrange
        Teacher teacher = new Teacher("Minerva", "McGonagall", null, "Potions", LocalDate.now());

        //act
        String fullName = teacher.getFullName();

        //assert
        assertEquals("Minerva McGonagall", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions", LocalDate.now());

        // act
        teacher.setFullName("Severus Prince Snape");

        // assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions",  LocalDate.now());

        // act
        teacher.setFullName("Minerva McGonagall");

        // assert
        assertEquals("Minerva", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("McGonagall", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions", LocalDate.now());

        // act
        teacher.setFullName("Minerva");

        // assert
        assertEquals("Minerva", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions", LocalDate.now());

        // act
        teacher.setFullName("Albus Percival Wulfric Brian Dumbledore");

        // assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Percival Wulfric Brian", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions", LocalDate.now());

        // act
        teacher.setFullName("");

        // assert
        assertEquals("", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, "Potions", LocalDate.now());

        // act
        teacher.setFullName(null);

        // assert
        assertNull(teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void CapitalizeIndividualNames() {
        // arrange
        Teacher teacher = new Teacher("severus", "prince", "snape", null, "Potions", LocalDate.now());

        // act
        teacher.capitalize(teacher.getFullName());

        // assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization() {
        // arrange
        Teacher teacher = new Teacher("sEvErUs", "PrInCe", "snaPE", null, "Potions", LocalDate.now());

        // act
        teacher.capitalize(teacher.getFullName());

        // assert
        assertEquals("Severus", teacher.getFirstName());
        assertEquals("Prince", teacher.getMiddleName());
        assertEquals("Snape", teacher.getLastName());
    }
}