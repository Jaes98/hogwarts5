package dk.kea.dat3js.hogwarts5.prefect;


import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefectService {

    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public PrefectService(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;

    }


    public List<Student> getAllPrefects() {
        System.out.println("Getting all prefects");
        return studentRepository.findAllByPrefectIsTrue();
    }

    public Optional<Student> getPrefectById(int id) {

        Optional<Student> optionalPrefect = studentRepository.findById(id);
        if (optionalPrefect.isPresent() && optionalPrefect.get().isPrefect()) {
            return optionalPrefect;
        } else {
            return Optional.empty();
        }
    }

    public void appointPrefect(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = student.get();
            if (!studentToUpdate.isPrefect()) {
                if (studentToUpdate.getSchoolYear() < 5) {
                    System.out.println("Student is not at year 5 yet.");
                }
                List<Student> prefectsInHouse = studentRepository.findAllByHouseNameAndPrefectIsTrue(studentToUpdate.getHouse().getName());
                System.out.println("Prefects in house: " + prefectsInHouse.size());
                if (prefectsInHouse.size() == 2) {
                    System.out.println("House already has two prefects");

                }

                boolean sameGenderPrefectExists = prefectsInHouse.stream().anyMatch(prefect -> prefect.getGender().equalsIgnoreCase(studentToUpdate.getGender()));
                if (sameGenderPrefectExists) {
                    System.out.println("House already has a prefect");

                }

                studentToUpdate.setPrefect(true);
                studentRepository.save(studentToUpdate);
            } System.out.println("Student is already a prefect");

        } else {
            System.out.println("Student not found");
            throw new IllegalArgumentException("Student not found");
        }

    }

    public List<StudentResponseDTO> getPrefectsByHouse(String house) {
        return studentRepository.findAllByHouseNameAndPrefectIsTrue(house).stream().map(studentService::toDTO).toList();
    }

    public void removePrefect(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = student.get();
            System.out.println("ER den prefect nu? " + studentToUpdate.isPrefect() + " " + studentToUpdate.getFullName());
            if (studentToUpdate.isPrefect() == true) {
                studentToUpdate.setPrefect(false);
                studentRepository.save(studentToUpdate);
            } else System.out.println("Student is not a prefect");
        } else {
            System.out.println("Student not found");
            throw new IllegalArgumentException("Student not found");
        }
    }
}
