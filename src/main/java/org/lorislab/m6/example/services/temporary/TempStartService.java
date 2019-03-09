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

import lombok.extern.slf4j.Slf4j;
import org.lorislab.m6.api.AbstractMessageListener;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/queue/tempStart"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "nextDestination", propertyValue = "tempTest")
        }
)
public class TempStartService extends AbstractMessageListener<TextMessage> {

    @Inject
    private JMSContext context;

    @Override
    public JMSContext getContext() {
        return context;
    }

    @Override
    protected void executeMessage(Message input, TextMessage output) throws JMSException {
        log.info("####### Temp Start service ####");
        output.setText("Temp Start service");
    }

}
