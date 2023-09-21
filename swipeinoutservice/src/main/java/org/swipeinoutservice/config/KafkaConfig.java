package org.swipeinoutservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.swipeinoutservice.entity.EmployeeAttendence;

@Configuration
public class KafkaConfig {

	private static final Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);
	
	@Bean
	public ProducerFactory<String, EmployeeAttendence> producerFactory() { 
		Map<String, Object> configMap = new HashMap<String, Object>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:9092");
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG	, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG	, JsonSerializer.class);
		LOG.debug("Initialized DefaultKafkaProducerFactory using configMap :: ",configMap);
		return new DefaultKafkaProducerFactory<>(configMap);
	}
	
	@Bean
	public KafkaTemplate<String, EmployeeAttendence> getKafkaTemplate() {
		return new KafkaTemplate<String, EmployeeAttendence>(producerFactory());
	}
	
}
