package connection;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sessionFactory;
	public static final String MybatisConfResource = "mybatis-config.xml";
	public static final String MybatisConfTestResource = "mybatis-config-main.xml";
	private MyBatisUtil() {
	}
	
	private static SqlSessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try(InputStream is = Resources.getResourceAsStream(MybatisConfResource)){
				sessionFactory = new SqlSessionFactoryBuilder().build(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static SqlSession getSession() {
		return getSessionFactory().openSession();
	}
	
//main test
	private static SqlSessionFactory getSessionFactoryTest() {
		if (sessionFactory == null) {
			try(InputStream is = Resources.getResourceAsStream(MybatisConfTestResource)){
				sessionFactory = new SqlSessionFactoryBuilder().build(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
	
	public static SqlSession getSessionTest() {
		return getSessionFactoryTest().openSession();
	}
}
