package com.nemesis.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nemesis.api.constants.ActionType;
import com.nemesis.api.constants.NodeInfo;
import com.nemesis.api.constants.NodeInfoType;
import com.nemesis.api.model.selenium.Browser;
import com.nemesis.api.model.selenium.Node;
import com.nemesis.api.repository.SeleniumGridRepository;
import com.nemesis.api.service.SeleniumGridService;

@Service("seleniumGridService")
@Scope("singleton")
public class SeleniumGridServiceImpl implements SeleniumGridService {

	@Autowired
	private SeleniumGridRepository seleniumGridRepository;

	@Override
	public Node getNodeByHost(String host) {
		Node node = seleniumGridRepository.findByNodeHost(host);
		return node;
	}

	@Override
	public void loadAndSaveNode(String host, List<String> browserNames) throws JSONException {
		Node node = new Node();
		List<Browser> browsers = new ArrayList<Browser>();
		for (String bn : browserNames) {
			Browser browser = new Browser();
			browser.setName(bn);

			NodeInfo ni = NodeInfo.getInfoTypes(bn, NodeInfoType.BROWSER_VERSION);
			if (ni != null) {
				String nodeInfoValue = seleniumGridRepository.nodeInfo(ni, host);
				if (nodeInfoValue != null) {
					JSONObject jsonObject = new JSONObject(nodeInfoValue);
					JSONArray a = jsonObject.getJSONArray("out");
					browser.setVersion(StringUtils.deleteWhitespace(a.getString(0)));
				}
			}

			ni = NodeInfo.getInfoTypes(bn, NodeInfoType.BROWSER_DRIVER_VERSION);
			if (ni != null) {
				String nodeInfoValue = seleniumGridRepository.nodeInfo(ni, host);
				if (nodeInfoValue != null) {
					JSONObject jsonObject = new JSONObject(nodeInfoValue);
					JSONArray a = jsonObject.getJSONArray("out");
					browser.setDriverVersion(StringUtils.deleteWhitespace(a.getString(0)));
				}
			}

			ni = NodeInfo.getInfoTypes(bn, NodeInfoType.BROWSER_PROC);
			if (ni != null) {
				String nodeInfoValue = seleniumGridRepository.nodeInfo(ni, host);
				if (nodeInfoValue != null) {
					JSONObject jsonObject = new JSONObject(nodeInfoValue);
					int numOfProc = jsonObject.getInt("numOfProc");
					browser.setNumOfRunning(numOfProc);
				}
			}

			browsers.add(browser);
		}

		node.setNodeHost(host);
		node.setBrowsers(browsers);
		node.setLastSync(LocalDateTime.now());

		// Delete exists record if node already exists in the mongodb
		Node exNode = seleniumGridRepository.findByNodeHost(host);
		if (exNode != null) {
			seleniumGridRepository.delete(new Query(Criteria.where("nodeHost").is(host)));
		}

		seleniumGridRepository.create(node);
	}

	@Override
	public String nodeAction(ActionType actionType, String nodeRemoteHost) {
		return seleniumGridRepository.nodeAction(actionType, nodeRemoteHost);
	}

	@Override
	public boolean reloadAllNodeInfo() {
		try {
			String hubInfoS = seleniumGridRepository.getHubInfo();
			JSONObject hubInfo = new JSONObject(hubInfoS);
			JSONArray nodes = hubInfo.getJSONArray("nodes");
			for (int i = 0; i < nodes.length(); i++) {
				JSONObject node = nodes.getJSONObject(i);
				JSONArray browsers = nodes.getJSONObject(i).getJSONArray("browsersSummary");
				List<String> bns = new ArrayList<String>();
				for (int j = 0; j < browsers.length(); j++) {
					JSONObject bjo = browsers.getJSONObject(j);
					int maxInstances = bjo.getInt("maxInstances");
					if (maxInstances > 0) {
						String bn = bjo.getString("browser");
						bns.add(bn);
					}
				}
				loadAndSaveNode(node.getString("nodeUrl"), bns);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public JSONObject getHubInfo() {
		try {
			String hubInfoS = seleniumGridRepository.getHubInfo();
			JSONObject hubInfo = new JSONObject(hubInfoS);
			JSONArray nodes = hubInfo.getJSONArray("nodes");
			for (int i = 0; i < nodes.length(); i++) {
				JSONObject nodeJo = nodes.getJSONObject(i);

				String nodeUrl = nodeJo.getString("nodeUrl");

				Node node = seleniumGridRepository.findByNodeHost(nodeUrl);

				JSONArray browsers = nodes.getJSONObject(i).getJSONArray("browsersSummary");

				for (int j = 0; j < browsers.length(); j++) {
					JSONObject bjo = browsers.getJSONObject(j);
					String bn = bjo.getString("browser");
					Browser browser = getBrowserByName(node, bn);
					if (browser != null) {
						if (browser.getName().equalsIgnoreCase("chrome")) {
							bjo.put("driverVersion",
									StringUtils.removeStart(browser.getDriverVersion(), "ChromeDriver"));
						} else {
							bjo.put("driverVersion", browser.getDriverVersion());
						}
						
						bjo.put("numOfProc", browser.getNumOfRunning());
						bjo.put("browserVersion", browser.getVersion());
					}
				}
			}
			return hubInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private Browser getBrowserByName(Node node, String name) {
		List<Browser> browsers = node.getBrowsers();
		if (browsers == null) {
			return null;
		}
		for (Browser browser : browsers) {
			if (browser.getName().equalsIgnoreCase(name)) {
				return browser;
			}
		}
		return null;
	}
}
