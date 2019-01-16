package com.cb.admin.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.admin.bo.EntityBO;
import com.cb.admin.component.StateComponent;
import com.cb.admin.web.service.EntityService;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private StateComponent state;
	
	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Override
	public Map<String, EntityBO> getEntities(){
		return state.getEntities();
	}
	
	@Override
	public EntityBO getEntity(String key){
		return state.getEntities().get(key);
	}
	
	@Override
	public List<?> queryEntities(String key) {
		EntityBO entity = this.getEntity(key);
		
		EntityManager entityManager = emf.createEntityManager();
		SessionImpl session = (SessionImpl)entityManager.getDelegate();
		//Transaction tx = (Transaction) session.beginTransaction();
		Query query = session.createQuery("from "+entity.getName());
		List list = query.list();
		session.close();
		return list;
	}

	@Override
	public void deleteEntities(String key, List<String> identifiers) {
		EntityBO entity = this.getEntity(key);
		
		for(String identifier : identifiers) {
			
			EntityManager entityManager = emf.createEntityManager();
			SessionImpl session = (SessionImpl)entityManager.getDelegate();
			Transaction tx = (Transaction) session.beginTransaction();
			Query query = session.createQuery("from "+entity.getName()+" where " + entity.getIdentifier() + " = '" + identifier + "'");
			Object obj = query.getSingleResult();
			session.delete(obj);
			tx.commit();
			session.close();
		}
	}
}
