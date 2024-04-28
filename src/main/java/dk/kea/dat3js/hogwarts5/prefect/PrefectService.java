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


    public List<StudentResponseDTO> getAllPrefects() {
        return studentRepository.findAllByIsPrefectIsTrue().stream().map(studentService::toDTO).toList();
    }

    public Optional<Student> getPrefectById(int id) {

        Optional<Student> optionalPrefect = studentRepository.findById(id);
        if (optionalPrefect.isPresent() && optionalPrefect.get().getIsPrefect()) {
            return optionalPrefect;
        } else {
            return Optional.empty();
        }
    }

    public void appointPrefect(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = student.get();
            if (!studentToUpdate.getIsPrefect()) {
                studentToUpdate.setIsPrefect(true);
                studentRepository.save(studentToUpdate);
            } System.out.println("Student is already a prefect");
        } else {
            System.out.println("Student not found");
        }

    }

    public List<StudentResponseDTO> getPrefectsByHouse(String house) {
        return studentRepository.findAllByHouseNameAndIsPrefectIsTrue(house).stream().map(studentService::toDTO).toList();
    }

    public void removePrefect(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = student.get();
            if (studentToUpdate.getIsPrefect()) {
                studentToUpdate.setIsPrefect(false);
                studentRepository.save(studentToUpdate);
            } System.out.println("Student is not a prefect");
        } else {
            System.out.println("Student not found");
        }
    }
}
