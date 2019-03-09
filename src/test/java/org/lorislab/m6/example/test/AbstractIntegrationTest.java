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

package org.lorislab.m6.example.test;

import io.restassured.RestAssured;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

@IntegrationTest
public class AbstractIntegrationTest {

    static {
        RestAssured.baseURI = "http://localhost:8080/m6/";
    }

    static final DockerComposeContainer DOCKER_COMPOSE;

    static {
        DOCKER_COMPOSE = new DockerComposeContainer(new File("docker-compose-test.yml"))
                .withPull(false)
                .withTailChildContainers(false)
                .withLogConsumer("m6-example", new DockerComposeLogger("m6-example"))
//            .withLogConsumer("m6-amq", new DockerComposeLogger("m6-amq"))
                .withExposedService("m6-example", 8080, Wait.forLogMessage(".*WildFly Full 16.0.0.Final.*started in.*", 1));
        DOCKER_COMPOSE.start();
    }
}
