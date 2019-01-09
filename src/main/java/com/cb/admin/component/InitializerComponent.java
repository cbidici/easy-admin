package com.cb.admin.component;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cb.admin.bo.AttributeBO;
import com.cb.admin.builder.AttributeBOBuilder;
import com.cb.admin.builder.EntityBOBuilder;

@Component
public class InitializerComponent {

	@Autowired
	private StateComponent stateComponent;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		EntityBOBuilder builder = new EntityBOBuilder();

		Set<EntityType<?>> listOfEntities = em.getMetamodel().getEntities();
		for (EntityType<?> type : listOfEntities) {
			Set<Attribute<?, ?>> attributes = getAttributes(type);
			Set<AttributeBO> attributeBOs = new HashSet<>();
			for (Attribute<?, ?> attribute : attributes) {
				attributeBOs
						.add(new AttributeBOBuilder().type(attribute.getJavaType()).identifier(attribute.getName()).name(attribute.getName()).build());
			}

			stateComponent.addEntity(builder.type(type.getJavaType()).name(type.getName())
					.category(type.getJavaType().getPackage().getName()).attributes(attributeBOs)
					.cls(type.getJavaType()).build());
		}
	}

	private Set<Attribute<?, ?>> getAttributes(EntityType<?> type) {
		HashSet<Attribute<?, ?>> attributes = new HashSet<Attribute<?, ?>>(type.getDeclaredAttributes());
		if (type.getSupertype() != null) {
			attributes.addAll(getAttributes(type.getSupertype()));
		}
		return attributes;
	}

	private Set<Attribute<?, ?>> getAttributes(IdentifiableType<?> type) {
		HashSet<Attribute<?, ?>> attributes = new HashSet<Attribute<?, ?>>(type.getDeclaredAttributes());
		if (type.getSupertype() != null) {
			attributes.addAll(getAttributes(type.getSupertype()));
		}
		return attributes;
	}
}
