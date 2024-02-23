package com.kafkaapps.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafkaapps.model.Person;

@Configuration
public class KafkaConfig {
	{
		System.out.println("Configured");
	}
	 @Bean
	    public ConsumerFactory<String, Person> consumerFactory() 
	    { 
	  
		 JsonDeserializer<Person> deserializer = new JsonDeserializer<>(Person.class);
		    deserializer.setRemoveTypeHeaders(false);
		    deserializer.addTrustedPackages("*");
		    deserializer.setUseTypeMapperForKey(true);

	        // Creating a Map of string-object pairs 
	        Map<String, Object> config = new HashMap<>(); 
	  
	        // Adding the Configuration 
	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
	                   "127.0.0.1:9092"); 
	        config.put(ConsumerConfig.GROUP_ID_CONFIG, 
	                   "people-reader"); 
	        config.put( 
	            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
	            StringDeserializer.class); 
	        config.put( 
	            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
	            deserializer); 
	        
	  
	        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),deserializer); 
	    } 
	 
	 @Bean
	 public ConcurrentKafkaListenerContainerFactory 
	    concurrentKafkaListenerContainerFactory() 
	    { 
	        ConcurrentKafkaListenerContainerFactory< 
	            String, Person> factory 
	            = new ConcurrentKafkaListenerContainerFactory<>(); 
	        factory.setConsumerFactory(consumerFactory()); 
	        return factory; 
	    } 
}
