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

package org.lorislab.m6.example.services.remote;

import lombok.extern.slf4j.Slf4j;
import org.jboss.ejb3.annotation.ResourceAdapter;
import org.lorislab.m6.api.AbstractMessageListener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Slf4j
@ResourceAdapter("remote-artemis")
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/remoteTest31"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "nextDestination", propertyValue = "remoteTest5")
        }
)
public class RemoteTest31Service extends AbstractMessageListener<TextMessage> {

    @Inject
    @JMSConnectionFactory("java:/jms/remoteCF")
    private JMSContext context;

    @Override
    public JMSContext getContext() {
        return context;
    }

    @Override
    protected void executeMessage(Message input, TextMessage output) throws JMSException {
        log.info("####### Test31 service ####");
        output.setText("Test 31");
        try {
            Client client = ClientBuilder.newClient();
            String name = client.target("http://m6-test:8080/m6-test/ping").request(MediaType.TEXT_PLAIN).get(String.class);
            log.info("Rest result:{}", name);
        } catch (Exception ex) {
            throw new RuntimeException("Error reading rest service information!", ex);
        }

    }

}
