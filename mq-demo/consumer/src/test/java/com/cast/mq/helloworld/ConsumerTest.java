package com.cast.demo.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {

    public static void main(String[] args) throws IOException, TimeoutException {
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

        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println("receive message：【" + message + "】");
            }
        });
        System.out.println("waiting 。。。。");
    }
}
