package me.mrletsplay.streamdeck.deck;

import java.util.Base64;

import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.json.converter.JSONConstructor;
import me.mrletsplay.mrcore.json.converter.JSONConvertible;
import me.mrletsplay.mrcore.json.converter.JSONValue;
import me.mrletsplay.streamdeck.action.Action;

public class ButtonState implements JSONConvertible {
	
	@JSONValue
	private Action action;
	
	private byte[] bitmap;
	
	@JSONConstructor
	private ButtonState() {}
	
	public ButtonState(Action action, byte[] bitmap) {
		this.action = action;
		this.bitmap = bitmap;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}
	
	public void setBitmap(byte[] bitmap) {
		this.bitmap = bitmap;
	}

	public byte[] getBitmap() {
		return bitmap;
	}

	@Override
	public void preSerialize(JSONObject object) {
		if(bitmap != null) object.put("bitmap", Base64.getEncoder().encodeToString(bitmap));
	}
	
	@Override
	public void preDeserialize(JSONObject object) {
		if(object.has("bitmap")) bitmap = Base64.getDecoder().decode(object.getString("bitmap"));
	}
	
	public ButtonState copy() {
		return new ButtonState(action, bitmap);
	}
	
}
