package scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import rate.NazBankConnect;
import rate.OndaConnect;
import rate.RateCalc;

public class Scheduler extends TimerTask {
 
    @Override
    public void run() {
    	
    	Date sysDate = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DAY_OF_YEAR, -6);
    	Date oldDate = calendar.getTime();
    	float newRate = 0;
    	float oldRate = 0;

    	NazBankConnect nB = new NazBankConnect();
    	OndaConnect oC = new OndaConnect();
    	RateCalc rateCalc = new RateCalc();
		
    	Integer byrUsdNewRate =  nB.NBGetData(sysDate);
		Integer byrUsdOldRate =  nB.NBGetData(oldDate);
		
		Float usdCrcNewRate = oC.ondaGetData(sysDate);
		Float usdCrcOldRate = oC.ondaGetData(oldDate);
		
		if (byrUsdNewRate!=null&&!byrUsdNewRate.equals(0)&&usdCrcNewRate!=null&&!usdCrcNewRate.equals(0f)){
			newRate = byrUsdNewRate.floatValue()/usdCrcNewRate.floatValue();
			System.out.println("resultRate = " + newRate);
		}
		
		if (byrUsdOldRate!=null&&!byrUsdOldRate.equals(0)&&usdCrcOldRate!=null&&!usdCrcOldRate.equals(0f)){
			oldRate = byrUsdOldRate.floatValue()/usdCrcOldRate.floatValue();
			System.out.println("oldRate = " + oldRate);
		}
		
		rateCalc.calcRateChange(newRate, oldRate);
    }
}
