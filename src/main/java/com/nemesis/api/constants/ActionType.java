package com.nemesis.api.constants;

public enum ActionType {

	KILL_ALL_FIREFOX("kill_ff"), KILL_ALL_IE("kill_ie"), KILL_ALL_CHROME("kill_chrome"), KILL_ALL_SAFARI("kill_safari"), KILL_ALL_OPERA(
			"kill_opera"), STOP_NODE("stop_node"), DOWNLOAD_CHROME_DRIVER("download_chrome_driver"), DOWNLOAD_IE_SERVER(
			"download_ie_server"), TAKE_SCREENSHOT("take_screenshot"), MOVE_MOUSE("move_mouse");

	private String value;

	private ActionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ActionType getEnum(String action) {
		ActionType[] enums = ActionType.values();

		for (ActionType actionType : enums) {
			if (actionType.getValue().equalsIgnoreCase(action)) {
				return actionType;
			}
		}
		return null;
	}

}
