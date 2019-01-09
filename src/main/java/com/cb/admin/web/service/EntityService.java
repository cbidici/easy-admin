package com.cb.admin.web.service;

import java.util.List;
import java.util.Map;

import com.cb.admin.bo.EntityBO;

public interface EntityService {

	Map<String, EntityBO> getEntities();

	List<?> queryEntities(String key);

	EntityBO getEntity(String key);

}
