package org.swipeinoutservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("swipeinout-service")
public class Configuration {

	private int noOfMins;
	
	private String topicName;

	public int getNoOfMins() {
		return noOfMins;
	}

	public void setNoOfMins(int noOfMins) {
		this.noOfMins = noOfMins;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
