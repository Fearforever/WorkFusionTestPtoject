package scheduler;

import java.sql.SQLException;
import java.util.Date;
import java.util.TimerTask;

import DAO.RateDAO;
import DAO.impl.RateDAOImpl;
import rate.NazBankConnect;
import rate.OndaConnect;
import rate.RateCalc;
import rate.RateEntity;

public class Scheduler extends TimerTask {
 
    @Override
    public void run() {
    	
    	Date sysDate = new Date();
    	float newRate = 0;

    	NazBankConnect nB = new NazBankConnect();
    	OndaConnect oC = new OndaConnect();
    	RateCalc rateCalc = new RateCalc();
		
    	Float byrUsdNewRate =  nB.NBGetData(sysDate);
		Float usdCrcNewRate = oC.ondaGetData(sysDate);
		
		if (byrUsdNewRate!=null&&!byrUsdNewRate.equals(0)&&usdCrcNewRate!=null&&!usdCrcNewRate.equals(0f)){
			newRate = byrUsdNewRate.floatValue()/usdCrcNewRate.floatValue();
			rateCalc.calcRateChange(newRate);
			}
		try {
			RateEntity re = new RateEntity(newRate, new java.sql.Date(sysDate.getTime()));
			RateDAO rdi = new RateDAOImpl();
			rdi.addRate(re);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
}
