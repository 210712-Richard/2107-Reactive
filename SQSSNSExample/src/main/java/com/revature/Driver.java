package com.revature;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Driver {
	public static void main(String[] args) {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion("us-east-2").build();
		
		String queueUrl = sqs.getQueueUrl("myqueue").getQueueUrl();
		//sendMessage("Josh", sqs, queueUrl);
		
		for(Message m: receiveMessages(sqs, queueUrl)) {
			System.out.println(m.getBody());
			// ok, we read the message, let's remove it
			// sometimes, we'd have a response to put into some queue, but now we don't
			removeMessage(sqs, queueUrl, m);
		}
	}
	
	private static void removeMessage(AmazonSQS sqs, String queueUrl, Message m) {
		DeleteMessageRequest req = new DeleteMessageRequest()
				.withQueueUrl(queueUrl)
				.withReceiptHandle(m.getReceiptHandle());
		sqs.deleteMessage(req);
	}

	private static List<Message> receiveMessages(AmazonSQS sqs, String queueUrl) {
		ReceiveMessageRequest req = new ReceiveMessageRequest()
				.withQueueUrl(queueUrl)
				.withMaxNumberOfMessages(5)
				.withWaitTimeSeconds(20); // long polling
		return sqs.receiveMessage(req).getMessages();
	}

	public static void sendMessage(String message, AmazonSQS sqs, String queueUrl) {
		SendMessageRequest sendMsgRequest = new SendMessageRequest()
				.withQueueUrl(queueUrl)
				.withMessageBody(message);
				//.withDelaySeconds(5);
		sqs.sendMessage(sendMsgRequest);
	}
}
