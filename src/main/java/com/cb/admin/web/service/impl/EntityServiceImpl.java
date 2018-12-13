package com.cb.admin.web.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.admin.bo.EntityBO;
import com.cb.admin.component.StateComponent;
import com.cb.admin.web.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private StateComponent state;
	
	@Override
	public Set<EntityBO> getEntities(){
		return state.getEntities();
	}
}
