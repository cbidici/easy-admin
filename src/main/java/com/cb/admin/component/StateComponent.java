package com.cb.admin.component;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cb.admin.bo.EntityBO;

@Component
public class StateComponent {

	private Set<EntityBO> entities;
	
	public StateComponent() {
		this.entities = new HashSet<>();
	}
	
	public void addEntity(EntityBO entityBO) {
		this.entities.add(entityBO);
	}
	
	public Set<EntityBO> getEntities() {
		return this.entities;
	}
}
