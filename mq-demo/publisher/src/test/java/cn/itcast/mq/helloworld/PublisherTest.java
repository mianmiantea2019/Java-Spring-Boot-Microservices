package com.cast.demo.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PublisherTest {
    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("");
        factory.setPassword("");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);
        String message = "hello, rabbitmq!";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println("message sent：【" + message + "】");

        channel.close();
        connection.close();

    }
}
