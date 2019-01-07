package com.cb.admin.web.service;

import java.util.List;
import java.util.Map;

import com.cb.admin.bo.EntityBO;

public interface EntityService {

	public Map<String, EntityBO> getEntities();

	public List<?> queryEntities(String name);

}
