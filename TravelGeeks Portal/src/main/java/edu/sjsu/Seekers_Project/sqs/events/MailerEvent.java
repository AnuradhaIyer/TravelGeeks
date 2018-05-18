package edu.sjsu.Seekers_Project.sqs.events;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MailerEvent {

	private String id;
	private String category;
	private String placeName;
	private EventType eventType;

	private final static ObjectMapper JSON = new ObjectMapper();
	static {
		JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public MailerEvent() {

	}

	public MailerEvent(String id, String category, String placeName, EventType eventType) {
		super();
		this.id = id;
		this.category=category;
		this.placeName=placeName;
		this.eventType = eventType;
	}

	public String getId() {
		return id;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	
	public void setplaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getplaceName() {
		return placeName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public byte[] toJsonAsBytes() {
		try {
			return JSON.writeValueAsBytes(this);
		} catch (IOException e) {
			return null;
		}
	}

	public static MailerEvent fromJsonAsBytes(byte[] bytes) {
		try {
			return JSON.readValue(bytes, MailerEvent.class);
		} catch (IOException e) {
			return null;
		}
	}

	public String toJsonString() {
		try {
			return JSON.writeValueAsString(this);
		} catch (IOException e) {
			return null;
		}
	}

	public static MailerEvent fromJsonString(String json) {
		try {
			return JSON.readValue(json, MailerEvent.class);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "MailerEvent [id=" + id + ", eventType=" + eventType + "]";
	}

}
