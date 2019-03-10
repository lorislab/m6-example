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

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.UUID;

@Slf4j
public class RestLoggerTest {

    @Test
    public void dummyTest() {
        RestLogger.dummyTest(UUID.randomUUID().toString());
        RestLogger.dummyTest(null);
    }

    @Test
    public void logTest() {
        log.info("Unit test");
        MultivaluedMap<String, String> data = new MultivaluedHashMap<>();
        data.putSingle("key1", "value");
        RestLogger.log(log, data);
    }

}
