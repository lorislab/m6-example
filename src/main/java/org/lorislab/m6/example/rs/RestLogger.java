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
import org.slf4j.Logger;

import javax.ws.rs.core.MultivaluedMap;

@Slf4j
public class RestLogger {

    private RestLogger() {
        // empty constructor
    }

    public static void dummyTest(String data) {
      log.info("Dummy test method {}", data);
    }

    public static void log(Logger log, MultivaluedMap<String, String> data) {
        StringBuilder sb = new StringBuilder();
        if (data != null) {
            for (String key : data.keySet()) {
                String value = data.getFirst(key);
                sb.append(key).append(':').append(value).append(',');
            }
        }
        log.info("\n######################################\n SEND MESSAGE \n{}\n \n######################################", sb.toString());
    }
}
