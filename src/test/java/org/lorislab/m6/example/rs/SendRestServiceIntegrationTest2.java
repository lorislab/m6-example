/*
 * Copyright 2019 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lorislab.m6.example.rs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lorislab.m6.example.test.AbstractIntegrationTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
public class SendRestServiceIntegrationTest2 extends AbstractIntegrationTest {

    @Test
    public void pingTest() {
        Response response = RestAssured.given()
                .get("/send");
        Assertions.assertEquals("PONG", response.asString());
        log.info("Ping response: {}", response.asString());
    }

    @Test
    public void startLocalTest() throws Exception {
        Response response = RestAssured.given()
                .body("TEXT-MESSAGE-LOCAL")
                .post("/send/local");
        Assertions.assertEquals(204, response.getStatusCode());
    }

    @Test
    public void startTempTest() {
        Response response = RestAssured.given()
                .body("TEXT-MESSAGE-TEMP")
                .post("/send/temp");
        Assertions.assertEquals(204, response.getStatusCode());
    }

    @Test
    public void startRemoteTest() {
        Response response = RestAssured.given()
                .body("TEXT-MESSAGE-REMOTE")
                .post("/send/remote");
        Assertions.assertEquals(204, response.getStatusCode());
    }
}
