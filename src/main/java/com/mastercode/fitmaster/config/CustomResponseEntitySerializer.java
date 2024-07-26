package com.mastercode.fitmaster.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mastercode.fitmaster.dto.response.CustomResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CustomResponseEntitySerializer extends JsonSerializer<CustomResponseEntity<?>> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void serialize(CustomResponseEntity<?> response, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		JsonNode dataNode = objectMapper.valueToTree(response.data());

		if (response.data() != null) {
			if (dataNode.isObject()) {
				ObjectNode objectNode = (ObjectNode) dataNode;
				gen.writeStartObject();

				objectNode.fields().forEachRemaining(field -> {
					try {
						gen.writeObjectField(field.getKey(), field.getValue());
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				gen.writeEndObject();
			} else {
				objectMapper.writeValue(gen, response.data());
			}
		} else {
			gen.writeStartObject();

			gen.writeStringField("timestamp", LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
			if (response.title() != null) {
				gen.writeStringField("title", response.title());
			}
			if (response.message() != null) {
				gen.writeStringField("message", response.message());
			}

			gen.writeEndObject();
		}
	}
}
