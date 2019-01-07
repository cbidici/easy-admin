package com.cb.admin.component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cb.admin.bo.AttributeBO;
import com.cb.admin.bo.EntityBO;
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

		Set<EntityType<?>> listOfEntities = em.getMetamodel().getEntities();
		for (EntityType<?> type : listOfEntities) {
			stateComponent.addEntity(getEntityBO(type));
		}
	}

	private EntityBO getEntityBO(EntityType<?> type) {
		EntityBOBuilder builder = new EntityBOBuilder();
		EntityBO entityBO = builder.type(type.getJavaType()).name(type.getName())
				.category(type.getJavaType().getPackage().getName()).cls(type.getJavaType()).build();
		initAttributes(entityBO);
		return entityBO;
	}

	private void initAttributes(EntityBO entityBO) {
		List<AttributeBO> attributeBOs = new ArrayList<>();
		List<Field> fields = getAttributes(entityBO.getCls());
		for (Field field : fields) {
			AttributeBO attrBO = new AttributeBOBuilder().type(field.getType()).field(field.getName())
					.name(field.getName()).build();
			if (isIdentifier(field)) {
				attrBO.setIdentifier(true);
				entityBO.setIdentifier(field.getName());
			}
			attributeBOs.add(attrBO);
		}
		entityBO.setAttributes(attributeBOs);
	}

	private boolean isIdentifier(Field field) {
		boolean result = false;

		Annotation[] annotations = field.getAnnotations();
		for(Annotation annotation : annotations) {
			if (Id.class.isInstance(annotation)) {
				result = true;
			}			
		}

		return result;
	}

	private List<Field> getAttributes(Class<?> cls) {
		List<Field> attributes = new ArrayList<>();
		if (cls.getSuperclass() != null) {
			attributes.addAll(getAttributes(cls.getSuperclass()));
		}
		attributes.addAll(Arrays.asList(cls.getDeclaredFields()));
		return attributes;
	}
}
