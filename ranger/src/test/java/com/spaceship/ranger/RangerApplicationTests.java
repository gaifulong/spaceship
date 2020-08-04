package com.spaceship.ranger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = RangerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class RangerApplicationTests {

    @Test
    void contextLoads() {
    }

}
