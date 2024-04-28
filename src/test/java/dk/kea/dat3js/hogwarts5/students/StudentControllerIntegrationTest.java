package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StudentControllerIntegrationTest {

  @Autowired
  private WebTestClient webClient;

  @Test
    void notNull() {
      assertNotNull(webClient);
  }

  @Test
  void createStudentWithFullName() {
    webClient
            .post().uri("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
          {
                      "name": "Peter Heronimous Lind",
                      "house": "Gryffindor",
                      "schoolYear": 7
                    }
                    """)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNumber()
            .jsonPath("$.fullName").isEqualTo("Peter Heronimous Lind")
            .jsonPath("$.house").isEqualTo("Gryffindor")
            .jsonPath("$.schoolYear").isEqualTo(7);

  }

  @Test
  void createStudentWithNameParts() {
    webClient
            .post().uri("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
            {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "house": "Gryffindor",
                        "schoolYear": 7
                        }
                        """)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody().json("""
            {
                        "firstName": "Peter",
                        "middleName": "Heronimous",
                        "lastName": "Lind",
                        "house": "Gryffindor",
                        "schoolYear": 7
                        }
                        """);
  }

}
