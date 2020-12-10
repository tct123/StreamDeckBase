package me.mrletsplay.streamdeck.deck;

import java.util.ArrayList;
import java.util.List;

public class StreamDeck {

	private StreamDeckButton[] defaultButtons;
	private List<StreamDeckProfile> profiles;
	private StreamDeckProfile currentProfile;
	private int buttonCount;

	public StreamDeck(int buttonCount) {
		this.defaultButtons = new StreamDeckButton[buttonCount];
		for(int i = 0; i < buttonCount; i++) {
			defaultButtons[i] = new StreamDeckButton(i);
		}
		this.profiles = new ArrayList<>();
		this.buttonCount = buttonCount;
	}
	
	public int getButtonCount() {
		return buttonCount;
	}

	public StreamDeckProfile createNewProfile(String identifier) {
		StreamDeckProfile profile = new StreamDeckProfile(identifier, defaultButtons);
		profiles.add(profile);
		return profile;
	}

	public void deleteProfile(String identifier) {
		StreamDeckProfile pr = profiles.stream()
				.filter(p -> p.getIdentifier().equals(identifier))
				.findFirst().orElse(null);
		if(pr == null) return;
		profiles.remove(pr);
		if(currentProfile.equals(pr)) currentProfile = null;
	}

	public List<StreamDeckProfile> getProfiles() {
		return profiles;
	}

	public StreamDeckProfile getProfile(String identifier) {
		return profiles.stream()
				.filter(p -> p.getIdentifier().equals(identifier))
				.findFirst().orElse(null);
	}

	public void selectProfile(StreamDeckProfile profile) {
		this.currentProfile = profile;
	}

	public void selectProfile(String profile) {
		selectProfile(getProfile(profile));
	}

	public StreamDeckProfile getCurrentProfile() {
		return currentProfile;
	}
	
	public void setProfiles(List<StreamDeckProfile> profiles) {
		this.profiles.clear();
		this.profiles.addAll(profiles);
		if(!profiles.contains(currentProfile)) currentProfile = null;
	}
	
}
