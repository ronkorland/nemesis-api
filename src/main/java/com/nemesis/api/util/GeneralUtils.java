package com.nemesis.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class GeneralUtils {

	public static List<String> convertMapPermissionsToList(HashMap<String, Boolean> map) {
		List<String> list = new ArrayList<String>();
		if (!map.isEmpty()) {
			for (Entry<String, Boolean> entry : map.entrySet()) {
				if (entry.getValue()) {
					list.add(entry.getKey());
				}
			}
		}
		return list;
	}
	
}
