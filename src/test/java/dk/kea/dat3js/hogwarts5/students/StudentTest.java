package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullNameWithMiddleName() {
        //arrange
        Student student = new Student("Harry", "James", "Potter", null, 1);

        //act
        String fullName = student.getFullName();

        //assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void getFullNameWithoutMiddleName() {
        //arrange
        Student student = new Student("Cho", "Chang", null, 1);

        //act
        String fullName = student.getFullName();

        //assert
        assertEquals("Cho Chang", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Ron Bilius Weasley");

        // assert
        assertEquals("Ron", student.getFirstName());
        assertEquals("Bilius", student.getMiddleName());
        assertEquals("Weasley", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Luna Lovegood");

        // assert
        assertEquals("Luna", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Lovegood", student.getLastName());
    }

    @Test
    void setFullNameWithoutLastName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Luna");

        // assert
        assertEquals("Luna", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("Luna Xenophilius Godesen Lovegood");

        // assert
        assertEquals("Luna", student.getFirstName());
        assertEquals("Xenophilius Godesen", student.getMiddleName());
        assertEquals("Lovegood", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName("");

        // assert
        assertEquals("", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // arrange
        Student student = new Student("first", "middle", "last", null, 1);

        // act
        student.setFullName(null);

        // assert
        assertNull(student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void CapitalizeIndividualNames() {
        // arrange
        Student student = new Student("harry", "james", "potter", null, 1);

        // act
        student.capitalize(student.getFullName());

        // assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }

    @Test
    void capitalizeIndividualNamesWithCrazyCapitalization() {
        // arrange
        Student student = new Student("hArRy", "jaMes", "pOtTeR", null, 1);

        // act
        student.capitalize(student.getFullName());

        // assert
        assertEquals("Harry", student.getFirstName());
        assertEquals("James", student.getMiddleName());
        assertEquals("Potter", student.getLastName());
    }
}