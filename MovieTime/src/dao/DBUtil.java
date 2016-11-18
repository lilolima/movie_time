package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class DBUtil {
		
	private static DBUtil dbUtil = null;
	private EntityManagerFactory emf = null;
	
	private DBUtil() {
		emf = Persistence.createEntityManagerFactory("MOVIES");
	}

	public static DBUtil getInstance() { 
		if (dbUtil == null) {
			dbUtil = new DBUtil();
		}
		return dbUtil;		
	}
	
	public EntityManager getConn() { 
		return emf.createEntityManager();
	}

}
