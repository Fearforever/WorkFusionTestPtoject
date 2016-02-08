package rate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import parserXML.Parser;

public class OndaConnect 
{
	@SuppressWarnings("deprecation")
	public Float ondaGetData(Date date) {
		
		Float rate = 0f;
		DefaultHttpClient httpclient = new DefaultHttpClient();
	    try {
	      HttpHost target = new HttpHost("www.oanda.com", 80, "http");
	      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       
	      String requestString = "/rates/api/v1/rates/USD.xml?api_key" 
	    		  + "=WqMVnBZco4UKKdVOtpvqYXzk&decimal_places=5&date=" 
	    		  + dateFormat.format(date) 
	    		  + "&fields=midpoint&quote=CRC";

	      HttpGet getRequest = new HttpGet(requestString);	 
	      HttpResponse httpResponse = httpclient.execute(target, getRequest);
	      HttpEntity entity = httpResponse.getEntity();
	 
	      if (entity != null) {
		    Parser parser = new Parser();
		    rate = parser.parseOnda(EntityUtils.toString(entity));

	      } 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	httpclient.getConnectionManager().shutdown();
	    }
		return rate;
	}
}
