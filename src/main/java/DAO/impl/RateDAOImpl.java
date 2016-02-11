package DAO.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import util.HibernateUtil;

import DAO.RateDAO;
import rate.RateEntity;

public class RateDAOImpl implements RateDAO {

	public void addRate(RateEntity rate) throws SQLException{
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(rate);
            session.getTransaction().commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

	}

	public List<RateEntity> getRateForWeek() throws SQLException{
		List<RateEntity> result = new ArrayList<RateEntity>();
		Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	session.beginTransaction();
        	result = session.createQuery("from RateEntity order by date desc").setMaxResults(6).list();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
		return result;
	}

}
