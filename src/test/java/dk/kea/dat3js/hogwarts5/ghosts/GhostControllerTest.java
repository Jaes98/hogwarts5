package dk.kea.dat3js.hogwarts5.ghosts;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.house.HouseRepository;
import dk.kea.dat3js.hogwarts5.house.HouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GhostController.class)
@ComponentScan(basePackageClasses = {HouseService.class})
class GhostControllerTest {

   @MockBean
   private HouseRepository houseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGhost() {
        //arrange
        GhostController ghostController = new GhostController();

        //act
        var ghost = ghostController.getGhost("Nearly Headless Nick");

        //assert
        assertTrue(ghost.hasBody());
        assertEquals("Nearly Headless Nick", ghost.getBody().getName());
        assertEquals("Sir Nicholas de Mimsy-Porpington", ghost.getBody().getRealName());
        assertEquals("Gryffindor", ghost.getBody().getHouse());
    }

    @Test
    void getAllGhosts() throws Exception {
        when(houseRepository.findById("Gryffindor").thenReturn(Optional.of(new House("Gryffindor", "Godric Gryffindor", new String []{"red", "gold"}))),
        mockMvc.perform(get("/ghosts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Nearly Headless Nick"))
                .andExpect(jsonPath("$[0].realName").value("Sir Nicholas de Mimsy-Porpington"))
                .andExpect(jsonPath("$[0].house").value("Gryffindor"))
                .andExpect(jsonPath("$[1].name").value("The Grey Lady"));
    }

}