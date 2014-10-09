package com.nemesis.api.repository;

import com.nemesis.api.constants.ActionType;
import com.nemesis.api.constants.NodeInfo;
import com.nemesis.api.model.selenium.Node;

public interface SeleniumGridRepository extends MongoRepository<Node, String> {

	public Node findByNodeHost(String host);

	public String nodeAction(ActionType actionType, String nodeRemoteHost);

	public String nodeInfo(NodeInfo nodeInfo, String nodeRemoteHost);
	
	public String getHubInfo();

}
