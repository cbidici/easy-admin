package com.cb.admin.web.service.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

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
	public Set<EntityBO> getEntities(){
		return state.getEntities();
	}
	
	@Override
	public List<?> queryEntities(String name) {
		EntityManager entityManager = emf.createEntityManager();
		SessionImpl session = (SessionImpl)entityManager.getDelegate();
		//Transaction tx = (Transaction) session.beginTransaction();
		Query query = session.createQuery("from "+name);
//		try {
//			tx.commit();
//		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
//				| HeuristicRollbackException | SystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		List list = query.list();
		session.close();
		return list;
	}
}
