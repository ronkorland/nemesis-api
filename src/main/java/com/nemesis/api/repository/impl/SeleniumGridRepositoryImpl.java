package com.nemesis.api.repository.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nemesis.api.constants.ActionType;
import com.nemesis.api.constants.NodeInfo;
import com.nemesis.api.model.selenium.Node;
import com.nemesis.api.repository.SeleniumGridRepository;

@Repository
@Scope("singleton")
public class SeleniumGridRepositoryImpl extends MongoRepositoryImpl<Node, String> implements SeleniumGridRepository {

	@Autowired
	private RestTemplate restTemplate;

	public SeleniumGridRepositoryImpl() {
		super(Node.class);
	}

	@Override
	public String nodeAction(ActionType actionType, String nodeRemoteHost) {
		HashMap<String, String> urlVariables = new HashMap<String, String>();

		urlVariables.put("actionType", actionType.getValue());
		System.out.println("action: " + actionType.getValue());

		nodeRemoteHost = nodeRemoteHost + "/extra/NodeActionServlet?action={actionType}";

		System.out.println("node remote host: " + nodeRemoteHost);

		ResponseEntity<String> entity = restTemplate.getForEntity(nodeRemoteHost, String.class, urlVariables);
		String body = entity.getBody();
		return body;
	}

	@Override
	public String nodeInfo(NodeInfo ni, String nodeRemoteHost) {
		HashMap<String, String> urlVariables = new HashMap<String, String>();

		urlVariables.put("infoType", ni.getCmd());
		System.out.println("info type: " + ni.getCmd());

		nodeRemoteHost = nodeRemoteHost + "/extra/NodeInfoServlet?info={infoType}";

		System.out.println("node remote host: " + nodeRemoteHost);

		try {
			ResponseEntity<String> entity = restTemplate.getForEntity(nodeRemoteHost, String.class, urlVariables);
			String body = entity.getBody();
			return body;
		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Node findByNodeHost(String host) {
		Query query = Query.query(Criteria.where("nodeHost").is(host));
		Node node = findOne(query);
		return node;
	}

	@Override
	public String getHubInfo() {
		ResponseEntity<String> entity = restTemplate.getForEntity(
				"http://sjrtp-qehub.marketo.org:4444/grid/admin/RestServlet", String.class);
		return entity.getBody();
	}

}
