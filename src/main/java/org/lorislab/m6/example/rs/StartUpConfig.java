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

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/start",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "start",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test1",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test1",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test2",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test2",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test31",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test31",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test32",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test32",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3",
                                "redelivery-delay-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test4",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test4",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
//                        resourceAdapter = "remote-artemis",
                        name = "java:/queue/test5",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "test5",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                )
        }
)
@Startup
@Singleton
public class StartUpConfig {
}
