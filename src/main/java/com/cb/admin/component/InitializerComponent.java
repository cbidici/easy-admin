package com.cb.admin.component;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.metamodel.EntityType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.cb.admin.domain.Attribute;
import com.cb.admin.domain.Entity;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitializerComponent {

	private final EntityStore entityStore;
	private final EntityManager em;

	@PostConstruct
	public void init() {
		log.debug("BEGIN initialize entities.");
		Set<EntityType<?>> listOfEntities = em.getMetamodel().getEntities();
		for (EntityType<?> type : listOfEntities) {
			entityStore.addEntity(getEntity(type));
		}
		log.debug("END initialize entities.");
	}

	private Entity getEntity(EntityType<?> type) {
		var entity = Entity.builder()
				.type(type.getJavaType())
				.name(type.getName())
				.build();

		initAttributes(entity);
		return entity;
	}

	private void initAttributes(Entity entity) {
		List<Attribute> attributes = new ArrayList<>();
		List<Field> fields = getAttributes(entity.getType());
		for (Field field : fields) {
			Attribute attribute = Attribute.builder()
					.type(field.getType())
					.name(field.getName())
					.build();

			if (isIdentifier(field)) {
				attribute.setIdentifier(true);
			}
			attributes.add(attribute);
		}
		entity.setAttributes(attributes);
	}

	private boolean isIdentifier(Field field) {
		Annotation[] annotations = field.getAnnotations();
		for(Annotation annotation : annotations) {
			if (annotation instanceof Id) {
				return true;
			}			
		}
		return false;
	}

	private List<Field> getAttributes(Type type) {
		List<Field> attributes = new ArrayList<>();
		Class<?> cls = ((Class<?>)type);
		if (cls.getSuperclass() != null) {
			attributes.addAll(getAttributes(cls.getSuperclass()));
		}
		attributes.addAll(Arrays.asList(cls.getDeclaredFields()));
		return attributes;
	}
}
