package me.mrletsplay.streamdeck.deck;

import java.util.Arrays;
import java.util.stream.Collectors;

import me.mrletsplay.mrcore.json.JSONArray;
import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.json.converter.JSONConstructor;
import me.mrletsplay.mrcore.json.converter.JSONConverter;
import me.mrletsplay.mrcore.json.converter.JSONConvertible;
import me.mrletsplay.mrcore.json.converter.JSONValue;

public class StreamDeckProfile implements JSONConvertible {

	@JSONValue
	private String identifier;
	
	private StreamDeckButton[] buttons;

	@JSONConstructor
	private StreamDeckProfile() {}
	
	public StreamDeckProfile(String identifier, StreamDeckButton[] buttons) {
		this.identifier = identifier;
		this.buttons = Arrays.stream(buttons)
				.map(StreamDeckButton::copy)
				.toArray(StreamDeckButton[]::new);
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public StreamDeckButton[] getButtons() {
		return buttons;
	}

	public StreamDeckButton getButton(int id) {
		if(id < 0 || id >= buttons.length) return null;
		return buttons[id];
	}
	
	@Override
	public void preSerialize(JSONObject object) {
		object.put("buttons", new JSONArray(Arrays.stream(buttons)
				.map(b -> b.toJSON(false))
				.collect(Collectors.toList())));
	}
	
	@Override
	public void preDeserialize(JSONObject object) {
		this.buttons = object.getJSONArray("buttons").stream()
				.map(o -> JSONConverter.decodeObject((JSONObject) o, StreamDeckButton.class))
				.toArray(StreamDeckButton[]::new);
	}

}
