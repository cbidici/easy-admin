package com.cb.admin.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cb.admin.bo.EntityBO;

@Component
public class StateComponent {

	private Map<String, EntityBO> entities;
	
	public StateComponent() {
		this.entities = new HashMap<>();
	}
	
	public void addEntity(EntityBO entityBO) {
		this.entities.put(entityBO.getKey(), entityBO);
	}
	
	public Map<String, EntityBO> getEntities() {
		return this.entities;
	}
}
