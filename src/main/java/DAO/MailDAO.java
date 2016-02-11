package DAO;

import java.sql.SQLException;
import java.util.List;

import mail.MailEntity;

public interface MailDAO {
	
	public List<MailEntity> getMail() throws SQLException;
}
