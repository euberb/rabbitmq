package com.euberb.rabbit;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);	    
	    for(int x = 0; x < 100000; x++) {
	    	String message = "Hello World! " + x;
	    	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    	System.out.println(" [x] Sent '" + message + "'");
	    }
	    
	    channel.close();
	    connection.close();
	}
}
