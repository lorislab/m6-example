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

import org.testcontainers.containers.output.BaseConsumer;
import org.testcontainers.containers.output.OutputFrame;

public class DockerComposeLogger extends BaseConsumer<org.testcontainers.containers.output.Slf4jLogConsumer> {

    private static final String LINE_BREAK_REGEX = "((\\r?\\n)|(\\r))";

    private static final String LINE_BREAK_AT_END_REGEX = LINE_BREAK_REGEX + "$";

    private String prefix;

    public DockerComposeLogger(String service) {
        this.prefix = prefix(service);
    }

    protected String prefix(String service) {
        String result = " ";

        if (service != null) {
            if (service.length() < 14) {
                result = service + result.repeat(14 - service.length());
            } else {
                result = service.substring(0, 14);
            }
        } else {
            result = result.repeat(14);
        }
        return result + "| ";
    }

    @Override
    public void accept(OutputFrame outputFrame) {
        OutputFrame.OutputType outputType = outputFrame.getType();

        String utf8String = outputFrame.getUtf8String();
        utf8String = utf8String.replaceAll(LINE_BREAK_AT_END_REGEX, "");
        switch (outputType) {
            case END:
                break;
            case STDOUT:
                System.out.println(prefix + utf8String);
                break;
            case STDERR:
                System.err.println(prefix + utf8String);
                break;
            default:
                throw new IllegalArgumentException("Unexpected outputType " + outputType);
        }
    }
}

