package com.nemesis.api.service;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.nemesis.api.constants.ActionType;
import com.nemesis.api.model.selenium.Node;

public interface SeleniumGridService {

	public Node getNodeByHost(String host);

	public void loadAndSaveNode(String host, List<String> browserNames) throws JSONException;

	public String nodeAction(ActionType eActionType, String nodeRemoteHost);
	
	public boolean reloadAllNodeInfo();
	
	public JSONObject getHubInfo();

}
