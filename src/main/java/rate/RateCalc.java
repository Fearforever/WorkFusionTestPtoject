package rate;

import java.sql.SQLException;
import java.util.List;

import DAO.impl.RateDAOImpl;
import mail.MailSender;

public class RateCalc {
	
	public void calcRateChange(float newRate){
		MailSender mail = new MailSender();
		
		List<RateEntity> rateList = null;
		RateDAOImpl rdi = new RateDAOImpl();
		
		try {
			rateList = rdi.getRateForWeek();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (RateEntity rate : rateList){
			if(rate.getRate()!=0&&((newRate/rate.getRate())>=1.05||(newRate/rate.getRate())<=0.95)){
				mail.sendMail(newRate);
			}
		}
	}
}
