package utils;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import models.entities.Testing;


public class TestingSerializer implements JsonSerializer<Testing>{

	@Override
	public JsonElement serialize(Testing src, Type typeOfSrc, JsonSerializationContext ctx) {

		return null;
	}

}
