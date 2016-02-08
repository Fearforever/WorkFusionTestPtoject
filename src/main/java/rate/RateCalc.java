package rate;

import mail.MailSender;

public class RateCalc {
	
	public void calcRateChange(float newRate, float oldRate){
		MailSender mail = new MailSender();
		if (newRate!=0&&oldRate!=0){
			if ((oldRate*1.05)<newRate&&(oldRate*0.95)>newRate){
				mail.sendMail(newRate);
			}
		}
	}
}
