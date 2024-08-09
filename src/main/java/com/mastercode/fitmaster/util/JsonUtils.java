package com.mastercode.fitmaster.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	public static String toJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String[] convertStringToArray(String input) {
		// Remove surrounding brackets if present
		input = input.replaceAll("^\\[|\\]$", "");

		// Split the string by "\",\"" and trim quotes
		String[] split = input.split("\",\"");

		// Remove leading and trailing quotes from each element
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].replace("\"", "");
		}

		return split;
	}

	public static String[] getValues(Object object) {
		return convertStringToArray(toJson(object));
	}
}
