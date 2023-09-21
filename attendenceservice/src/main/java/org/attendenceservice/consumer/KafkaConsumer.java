package org.attendenceservice.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.attendenceservice.entity.Attendence;
import org.attendenceservice.service.AttendenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.swipeinoutservice.entity.EmployeeAttendence;

@Service
public class KafkaConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

	@Value("${kafka.consumer.topic}")
	private String topicName;

	@Autowired
	private AttendenceService attendenceService;

	@KafkaListener(topics = "${kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<String, EmployeeAttendence> payload) {
		LOG.info("Tópic: {}", topicName);
		LOG.info("key: {}", payload.key());
		LOG.info("Headers: {}", payload.headers());
		LOG.info("Partition: {}", payload.partition());
		LOG.info("attendence: {}", payload.value());
		EmployeeAttendence ea = payload.value();
		Attendence attendence = new Attendence(ea.getEmployeeId(), ea.getSwipeDate(), ea.getTotalTimeInOffice());
		attendenceService.save(attendence);
	}

}
