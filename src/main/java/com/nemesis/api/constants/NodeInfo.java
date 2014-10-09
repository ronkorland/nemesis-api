package com.nemesis.api.constants;

public enum NodeInfo {

	NUMBER_OF_FIREFOX("num_ff", "firefox", NodeInfoType.BROWSER_PROC), NUMBER_OF_CHROME("num_chrome", "chrome",
			NodeInfoType.BROWSER_PROC), NUMBER_OF_IE("num_ie", "internet explorer", NodeInfoType.BROWSER_PROC), GET_CHROME_DRIVER_VERSION(
			"get_chrome_driver_version", "chrome", NodeInfoType.BROWSER_DRIVER_VERSION), GET_IE_SERVER_VERSION(
			"get_ie_server_version", "internet explorer", NodeInfoType.BROWSER_DRIVER_VERSION), GET_IE_VERSION(
			"get_ie_version", "internet explorer", NodeInfoType.BROWSER_VERSION), GET_FIREFOX_VERSION("get_ff_version",
			"firefox", NodeInfoType.BROWSER_VERSION), GET_CHROME_VERSION("get_chrome_version", "chrome",
			NodeInfoType.BROWSER_VERSION);

	private String cmd;
	private String browser;
	private NodeInfoType type;

	private NodeInfo(String cmd, String browser, NodeInfoType type) {
		this.cmd = cmd;
		this.browser = browser;
		this.type = type;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public NodeInfoType getType() {
		return type;
	}

	public void setType(NodeInfoType type) {
		this.type = type;
	}

	public static NodeInfo getInfoTypes(String browser, NodeInfoType type) {
		for (NodeInfo it : NodeInfo.values()) {
			if (it.getBrowser().equalsIgnoreCase(browser) && it.getType() == type) {
				return it;
			}
		}
		return null;
	}

}
