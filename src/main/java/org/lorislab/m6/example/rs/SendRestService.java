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
import org.lorislab.m6.api.AbstractMessageListener;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.UUID;

@Slf4j
@Stateless
@Path("send")
public class SendRestService {

    @Inject
//    @JMSConnectionFactory("java:/jms/remoteCF")
    private JMSContext context;

    @POST
    public void send(@Context HttpHeaders headers, String data) throws Exception {
        String id = UUID.randomUUID().toString();
        log.info("\n######################################\n SEND MESSGAE: {} \n######################################", id);
        TextMessage msg = context.createTextMessage(data);
        AbstractMessageListener.copyHeader(headers.getRequestHeaders(), msg);
        msg.setStringProperty("M6_EXAMPLE_ID", id);
        Queue queue = context.createQueue("start");
        context.createProducer().send(queue, msg);
    }
}
