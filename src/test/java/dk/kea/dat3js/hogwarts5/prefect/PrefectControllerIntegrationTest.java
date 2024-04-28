package dk.kea.dat3js.hogwarts5.prefect;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PrefectControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void notNull() {
        assertNotNull(webClient);
    }

    @Test
    void getAllPrefects() {
        webClient.get().uri("/prefects")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void getPrefect() {

        webClient.get().uri("/prefects/4").exchange().expectStatus().isOk().expectBody().json("""
                {
                    "id": 4,
                    "firstName": "Ron",
                    "middleName": "Bilius",
                    "lastName": "Weasley",
                    "house": {
                        "name": "Gryffindor",
                        "founder": "Godric Gryffindor",
                        "colors": [
                            "red",
                            "gold"
                        ]
                    },
                    "schoolYear": 5,
                    "prefect": true,
                    "gender": "male",
                    "fullName": "Ron Bilius Weasley"
                }
                """);
    }

    @Test
    void getPrefectsByHouse() {
        webClient.get().uri("/prefects/house/Gryffindor")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void appointPrefect() {
        webClient.post().uri("/prefects/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void removePrefectFromNonPrefect() {
        webClient.delete().uri("/prefects/6")
                .exchange().expectStatus().isOk();
    }

    @Test
    void removePrefectFromExistingPrefect() {
        webClient.delete().uri("/prefects/4")
                .exchange().expectStatus().isOk();
//                .expectBody().json("""
//                {
//                    "firstName": "Ron",
//                    "middleName": "Bilius",
//                    "lastName": "Weasley",
//                    "fullName": "Ron Bilius Weasley"
//                    "house": "Gryffindor",
//                    "schoolYear": 5,
//                    "prefect": false,
//                    "gender": "male"
//
//                }
//                """);
    }
}
