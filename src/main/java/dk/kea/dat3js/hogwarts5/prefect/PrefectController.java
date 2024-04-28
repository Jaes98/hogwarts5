package dk.kea.dat3js.hogwarts5.prefect;


import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefects")
public class PrefectController {

    private final PrefectService prefectService;

    @Autowired
    public PrefectController(PrefectService prefectService) {
        this.prefectService = prefectService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllPrefects() {
        return prefectService.getAllPrefects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getPrefectById(@PathVariable int id) {
        return ResponseEntity.of(prefectService.getPrefectById(id));
    }

    @GetMapping("/house/{house}")
    public List<StudentResponseDTO> getPrefectsByHouse(@PathVariable String house){
        return prefectService.getPrefectsByHouse(house);
    }

    @PostMapping("/{id}")
    public void appointPrefect(@PathVariable int id) {
        prefectService.appointPrefect(id);
    }

    @DeleteMapping("/{id}")
    public void removePrefect(@PathVariable int id) {
        prefectService.removePrefect(id);
    }
}
