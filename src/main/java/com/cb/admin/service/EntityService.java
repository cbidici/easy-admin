package com.cb.admin.service;

import com.cb.admin.domain.Attribute;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.cb.admin.domain.Entity;
import com.cb.admin.component.EntityStore;

@Service
@RequiredArgsConstructor
public class EntityService {

	private final EntityStore store;
	@PersistenceUnit
	EntityManagerFactory emf;

	public Map<String, Entity> getEntities(){
		return store.getEntities();
	}

	public Entity getEntity(String typeName){
		return store.getEntity(typeName);
	}
	
	public List<?> queryEntities(String name) {
		Entity entity = store.getEntity(name);
		EntityManager entityManager = emf.createEntityManager();
		SessionImpl session = (SessionImpl)entityManager.getDelegate();
		Query<?> query = session.createQuery("from "+entity.getName());
		List<?> list = query.list();
		session.close();
		return list;
	}

	public void deleteEntity(String key, String identifier) {
		Entity entity = getEntity(key);
		String identifierName = entity.getAttributes().stream().filter(Attribute::isIdentifier).findFirst().orElseThrow().getName();

		EntityManager entityManager = emf.createEntityManager();
		SessionImpl session = (SessionImpl)entityManager.getDelegate();
		Transaction tx = session.beginTransaction();
		Query<?> query = session.createQuery("from "+entity.getName()+" where " + identifierName + " = '" + identifier + "'");
		Object obj = query.getSingleResult();
		session.remove(obj);
		tx.commit();
		session.close();
	}
}
