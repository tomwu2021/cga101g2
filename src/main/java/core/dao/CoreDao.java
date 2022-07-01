package core.dao;

import java.util.List;

import org.hibernate.Session;

import core.util.HibernateUtil;

public interface CoreDao<P, I> {
	Integer insert(P pojo);

	Integer deleteById(I id);

	Integer update(P pojo);

	P selectById(I id);

	List<P> selectAll();
	
	// Java 8 後，介面的方法可以有身體，但修飾字為 default
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
//		return HibernateUtil.getSessionFactory().openSession();
	}
}
