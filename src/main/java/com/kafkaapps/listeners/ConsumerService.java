package com.kafkaapps.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafkaapps.model.Person;

@Service
public class ConsumerService {

	
	@KafkaListener(topics = {"messages"},groupId="people-reader",containerFactory = "concurrentKafkaListenerContainerFactory")
	public void readData(Person person) {
		System.out.println(person);
	}
}
