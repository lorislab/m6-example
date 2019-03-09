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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@Slf4j
@Stateless
@Path("send")
public class SendRestService {

    @Inject
    private JMSContext context;

    @Inject
    @JMSConnectionFactory("java:/jms/remoteCF")
    private JMSContext remoteContext;

    @GET
    public String ping() {
        return "PONG";
    }

    @POST
    @Path("local")
    public void sendLocal(@Context HttpHeaders headers, String data) throws Exception {
        logHeaders(headers);
        sendMessage(context, headers, data, "localStart");
    }

    @POST
    @Path("temp")
    public void sendTemp(@Context HttpHeaders headers, String data) throws Exception {
        logHeaders(headers);
        sendMessage(context, headers, data, "tempStart");
    }

    @POST
    @Path("remote")
    public void sendRemote(@Context HttpHeaders headers, String data) throws Exception {
        logHeaders(headers);
        sendMessage(remoteContext, headers, data, "remoteStart");
    }

    private void sendMessage(JMSContext c, HttpHeaders h, String data, String queueName) throws Exception {
        TextMessage msg = c.createTextMessage(data);
        AbstractMessageListener.copyHeader(h.getRequestHeaders(), msg);
        Queue queue = c.createQueue(queueName);
        c.createProducer().send(queue, msg);
    }

    private void logHeaders(HttpHeaders headers) {
        StringBuilder sb = new StringBuilder();
        if (headers.getRequestHeaders() != null) {
            for (String key : headers.getRequestHeaders().keySet()) {
                String value = headers.getRequestHeaders().getFirst(key);
                sb.append(key).append(':').append(value).append(',');
            }
        }
        log.info("\n######################################\n SEND MESSAGE \n{}\n \n######################################", sb.toString());
    }
}
