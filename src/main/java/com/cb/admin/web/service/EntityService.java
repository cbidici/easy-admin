package com.cb.admin.web.service;

import java.util.List;
import java.util.Set;

import com.cb.admin.bo.EntityBO;

public interface EntityService {

	public Set<EntityBO> getEntities();

	public List<?> queryEntities(String name);

}
