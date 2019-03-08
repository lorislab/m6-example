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

package org.lorislab.m6.example.services.temporary;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions(
        value = {
                @JMSDestinationDefinition(
                        name = "java:/queue/tempStart",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "tempStart",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
                        name = "java:/queue/tempTest",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "tempTest",
                        properties = {
                                "max-delivery-attempts=2",
                                "max-redelivery-delay=60000",
                                "redelivery-delay=3000",
                                "redelivery-multiplier=3"
                        }
                ),
                @JMSDestinationDefinition(
                        name = "java:/queue/tempEnd",
                        interfaceName = "javax.jms.Queue",
                        destinationName = "tempEnd",
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
public class TempStartUpConfig {
}
