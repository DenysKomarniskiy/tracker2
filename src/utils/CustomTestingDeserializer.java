package utils;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class CustomTestingDeserializer implements JsonDeserializer<CustomTesting> {

	@Override
	public CustomTesting deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject jsonObject = json.getAsJsonObject();

		CustomTesting customTesting = new CustomTesting();

		customTesting.name = jsonObject.get("name").getAsString();

		JsonArray list = jsonObject.getAsJsonArray("list");

		for (JsonElement item : list) {

			customTesting.items.add((CustomTestingItem) context.deserialize(item, CustomTestingItem.class));

		}
		
		JsonArray listUsers = jsonObject.getAsJsonArray("users");
		
		for (JsonElement user : listUsers) {

			customTesting.users.add(user.getAsString());

		}

		return customTesting;
	}

}
