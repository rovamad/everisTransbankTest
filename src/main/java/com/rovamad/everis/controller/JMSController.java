package com.rovamad.everis.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import com.rovamad.everis.setting.ConsumerMessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class JMSController {

    @ResponseBody
    @GetMapping(value = "/api/jms")
    public String startJMSMock() throws Exception {
        BrokerService broker = BrokerFactory.createBroker(new URI(
                "broker:(tcp://localhost:61616)"));
        broker.start();
        Connection connection = null;
        try {
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("customerQueue");

            // Consumer
            for (int i = 0; i < 20; i++) {
                MessageConsumer consumer = session.createConsumer(queue);
                consumer.setMessageListener(new ConsumerMessageListener(
                        "Consumer " + i));
            }
            connection.start();

            MessageProducer producer = session.createProducer(queue);
            for (int i = 0; i < 80; i++) {

                // Prepare header
                HttpHeaders headers = new HttpHeaders() {{
                    String authHeader = "Basic cm92YW1hZDpyb3ZhbWFk";
                    set("Authorization", authHeader);
                    set("Content-Type", "application/json");
                }};
                HttpEntity<String> entity = new HttpEntity<>(headers);

                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/api/sales";
                ResponseEntity<String> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        String.class);

                Message msg = session.createTextMessage(response.getBody());

                producer.send(msg);
            }

            session.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            broker.stop();
        }

        return "Tested";
    }
}
