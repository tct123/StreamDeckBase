package me.mrletsplay.streamdeck.deck;

import java.util.Arrays;
import java.util.stream.Collectors;

import me.mrletsplay.mrcore.json.JSONArray;
import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.json.converter.JSONConstructor;
import me.mrletsplay.mrcore.json.converter.JSONConverter;
import me.mrletsplay.mrcore.json.converter.JSONConvertible;
import me.mrletsplay.mrcore.json.converter.JSONValue;

public class StreamDeckButton implements JSONConvertible {

	@JSONValue
	private int id;
	
	private ButtonState[] states;
	
	private int currentState;
	
	@JSONConstructor
	private StreamDeckButton() {}

	public StreamDeckButton(int id) {
		this.id = id;
		this.states = new ButtonState[0];
	}

	public int getID() {
		return id;
	}

	public void setStates(ButtonState[] states) {
		this.states = states;
		this.currentState = 0;
	}
	
	public ButtonState[] getStates() {
		return states;
	}
	
	public ButtonState getState(int index) {
		if(index < 0 || index >= states.length) return null;
		return states[index];
	}
	
	public ButtonState getCurrentState() {
		return getState(currentState);
	}
	
	public void changeState() {
		currentState = (currentState + 1) % states.length;
	}
	
	public StreamDeckButton copy() {
		return new StreamDeckButton(id);
	}
	
	@Override
	public void preSerialize(JSONObject object) {
		object.put("states", new JSONArray(Arrays.stream(states)
				.map(s -> s.toJSON(false))
				.collect(Collectors.toList())));
	}
	
	@Override
	public void preDeserialize(JSONObject object) {
		this.states = object.getJSONArray("states").stream()
				.map(o -> JSONConverter.decodeObject((JSONObject) o, ButtonState.class))
				.toArray(ButtonState[]::new);
	}
	
}
