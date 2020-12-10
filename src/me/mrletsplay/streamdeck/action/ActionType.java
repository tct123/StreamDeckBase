package me.mrletsplay.streamdeck.action;

import me.mrletsplay.mrcore.json.converter.JSONPrimitiveStringConvertible;

public enum ActionType implements JSONPrimitiveStringConvertible {

	RUN_TERMINAL,
	TYPE_TEXT,
	SET_PROFILE,
	PRESS_FUNCTION_KEY;

	@Override
	public String toJSONPrimitive() {
		return name();
	}
	
	public static ActionType decodePrimitive(Object obj) {
		return valueOf((String) obj);
	}

}
