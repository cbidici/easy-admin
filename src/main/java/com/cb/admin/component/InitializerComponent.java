package com.cb.admin.component;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cb.admin.builder.EntityBOBuilder;

@Component
public class InitializerComponent {

	@Autowired
	private StateComponent stateComponent;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		Set<EntityType<?>> listOfEntities = em.getMetamodel().getEntities();
		for (EntityType<?> type : listOfEntities) {
			EntityBOBuilder builder = new EntityBOBuilder();
			stateComponent.addEntity(builder.type(type.getJavaType()).name(type.getName()).build());
		}
	}
}
