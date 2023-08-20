package com.cb.admin.component;

import com.cb.admin.exception.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

import java.util.Optional;
import org.springframework.stereotype.Component;

import com.cb.admin.domain.Entity;

@Component
public class EntityStore {

	private final Map<String, Entity> entities;
	
	public EntityStore() {
		this.entities = new HashMap<>();
	}
	
	public void addEntity(Entity entity) {
		this.entities.put(entity.getName(), entity);
	}
	
	public Map<String, Entity> getEntities() {
		return this.entities;
	}

	public Entity getEntity(String name) {
		return Optional.ofNullable(entities.get(name))
				.orElseThrow(() -> new EntityNotFoundException(name));
	}
}
