package DAO;

import java.sql.SQLException;
import java.util.List;

import rate.RateEntity;

public interface RateDAO {
	
	public void addRate(RateEntity rate) throws SQLException;
	public List<RateEntity> getRateForWeek() throws SQLException;

}
