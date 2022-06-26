package com.members.hibernate;

import static core.util.HibernateUtil.*;

import org.hibernate.Transaction;
import com.members.hibernate.pojo.MemberPojo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class TestDemo {

	// 修改
	public boolean updateByIdSaveOrUpdate(MemberPojo newMember) {

		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(newMember);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	// 修改
	public boolean updateByIdUpdate(MemberPojo newMember) {

		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.update(newMember);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	// 修改
	public boolean updateByIdUseSetter(MemberPojo newMember) {

		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			// 先透過 session.get()、session.load() 取得實體物件
			MemberPojo oldMember = session.load(MemberPojo.class, newMember.getMemberId());

			String name = newMember.getName();
			// 如果接收到的 name 不為空的話，就將值 set 到 oldMember ( 資料庫原本的值 )
			if (StringUtils.isNotEmpty(name)) {
				oldMember.setName(name);
			}
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	// remove 刪除
	public static Boolean deleteUseRemove(Integer id) {
		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			MemberPojo member = session.load(MemberPojo.class, id);
			session.remove(member);
			transaction.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	// delete 刪除
	public static Boolean deleteUseDelect(Integer id) {
		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			MemberPojo member = session.load(MemberPojo.class, id);
			session.delete(member);
			transaction.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	// persist 新增，不會回傳產生的識別值
	public Boolean insertUsePersist(MemberPojo member) {
		// 取得連線
		Session session = getSessionFactory().openSession();
		try {
			// beginTransaction 開始交易
			Transaction transaction = session.beginTransaction();
			// save 相當於 JDBC Insert，不會回傳識別值
			session.persist(member);
			// commit 交易結束
			transaction.commit();
			return true;
		} catch (Exception e) {
			// 當有 Exception，則 rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	// save 新增，會回傳產生的識別值
	public int insertUseSave(MemberPojo member) {
		// 取得連線
		Session session = getSessionFactory().openSession();
		try {
			// beginTransaction 開始交易
			Transaction transaction = session.beginTransaction();
			// save 相當於 JDBC Insert，會回傳識別值
			Integer id = (Integer) session.save(member);
			// commit 交易結束
			transaction.commit();
			return id;
		} catch (Exception e) {
			// 當有 Exception，則 rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return 0;
	}

	// get() 查詢
	public static MemberPojo selectByIdUseGet(Integer id) {
		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			MemberPojo member = session.get(MemberPojo.class, id);
			transaction.commit();
			return member;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	// load() 查詢
	public static MemberPojo selectByIdUseLoad(Integer id) {
		Session session = getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			MemberPojo member = session.load(MemberPojo.class, id);
			Hibernate.initialize(member);
			transaction.commit();
			return member;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
//		MemberPojo member = new MemberPojo();
//		member.setMemberId(3);
//		member.setName("Eden");
		TestDemo t1 = new TestDemo();
//		t1.updateByIdUseSetter(member);

		System.out.println(t1.selectByIdUseLoad(1).getName());

	}

}
