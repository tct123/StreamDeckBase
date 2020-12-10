package me.mrletsplay.streamdeck.action;

import me.mrletsplay.mrcore.json.JSONObject;
import me.mrletsplay.mrcore.json.converter.JSONConstructor;
import me.mrletsplay.mrcore.json.converter.JSONConvertible;
import me.mrletsplay.mrcore.json.converter.JSONValue;

public class Action implements JSONConvertible {

	@JSONValue
	private ActionType type;
	
	@JSONValue
	private JSONObject data;

	@JSONConstructor
	private Action() {}
	
	private Action(ActionType type, JSONObject data) {
		this.type = type;
		this.data = data;
	}
	
	public ActionType getType() {
		return type;
	}

	public JSONObject getData() {
		return data;
	}

	@StreamDeckAction(id = "run_in_terminal", name = "Run in Terminal")
	public static Action runTerminal(@StreamDeckActionParameter(name = "command", friendlyName = "Command") String command) {
		JSONObject data = new JSONObject();
		data.put("method_id", "run_in_terminal");
		data.put("command", command);
		return new Action(ActionType.RUN_TERMINAL, data);
	}

	@StreamDeckAction(id = "type_text", name = "Type Text")
	public static Action typeText(@StreamDeckActionParameter(name = "text", friendlyName = "Text") String text) {
		JSONObject data = new JSONObject();
		data.put("method_id", "type_text");
		data.put("text", text);
		return new Action(ActionType.TYPE_TEXT, data);
	}

	@StreamDeckAction(id = "set_profile", name = "Set Profile")
	public static Action setProfile(@StreamDeckActionParameter(name = "profile", friendlyName = "Profile") String profile) {
		JSONObject data = new JSONObject();
		data.put("method_id", "set_profile");
		data.put("profile", profile);
		return new Action(ActionType.SET_PROFILE, data);
	}

	@StreamDeckAction(id = "press_function_key", name = "Press Function Key")
	public static Action pressKey(@StreamDeckActionParameter(name = "key", friendlyName = "Key") FunctionKeyCode key) {
		JSONObject data = new JSONObject();
		data.put("method_id", "press_function_key");
		data.put("key", key.name());
		return new Action(ActionType.PRESS_FUNCTION_KEY, data);
	}

}
