package org.swipeinoutservice.producer;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.swipeinoutservice.config.Configuration;
import org.swipeinoutservice.entity.EmployeeAttendence;

@Service
public class KafkaProducer {
	
 	private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

	@Autowired
	private KafkaTemplate<String, EmployeeAttendence> kafkaTemplate;

	@Autowired
	private Configuration configuration;

	public void sendMessage(EmployeeAttendence empAttendence) {
		LOG.debug("Topic Name:: {}", configuration.getTopicName());
		CompletableFuture<SendResult<String, EmployeeAttendence>> future = kafkaTemplate
				.send(configuration.getTopicName(), empAttendence);
		
		future.whenComplete((result, ex) -> {
			if(ex == null) {
				LOG.info("Sent Message {} with Offset {}",empAttendence, result.getRecordMetadata().offset());
			} else {
				LOG.error("Unable to send message {} due to exception: {}",empAttendence, ex.getMessage());
			}
		});
		
		
	}

}
