package dao;

import javax.persistence.EntityManager;

import dao.DBUtil;
import entidade.Filme;

public class FilmeDAOImpl implements FilmeDAO {
	private EntityManager em;

	public FilmeDAOImpl() { 
		em = DBUtil.getInstance().getConn();
	}

	@Override
	public void adicionar(Filme f) {
		em.getTransaction().begin();
		em.persist( f );
		em.getTransaction().commit();
	}
}
