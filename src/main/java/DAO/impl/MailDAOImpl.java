package DAO.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import DAO.MailDAO;
import mail.MailEntity;
import util.HibernateUtil;

public class MailDAOImpl implements MailDAO {

	public List<MailEntity> getMail() throws SQLException {
		List<MailEntity> result = new ArrayList<MailEntity>();
		Session session = null;
        try {
        	session = HibernateUtil.getSessionFactory().openSession();
        	session.beginTransaction();
        	result = session.createQuery("from MailEntity").list();
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
