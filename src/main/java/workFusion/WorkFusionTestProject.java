package workFusion;

import java.util.Calendar;
import java.util.Timer;
import scheduler.Scheduler;

public class WorkFusionTestProject {
	
	public static void main(String[] args) {
		
		Timer time = new Timer();
        Scheduler st = new Scheduler();
		
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 9);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

//        time.schedule(st, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); 
        time.schedule(st, 0, 10000);
        
	}

}
