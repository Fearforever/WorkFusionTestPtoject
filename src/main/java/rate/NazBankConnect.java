package rate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import parserXML.Parser;

public class NazBankConnect {
	
	@SuppressWarnings("deprecation")
	public Integer NBGetData(Date date) {
		
		Integer rate = 0;
		DefaultHttpClient httpclient = new DefaultHttpClient();
	    try {
	    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    	
	    	HttpHost target = new HttpHost("www.nbrb.by", 80, "http");
	    	String requestString = "/Services/XmlExRates.aspx?ondate=" 
	    			+ dateFormat.format(date);
	      
	    	HttpGet getRequest = new HttpGet(requestString);
	 	 
	    	HttpResponse httpResponse = httpclient.execute(target, getRequest);
	    	HttpEntity entity = httpResponse.getEntity();
	 
	    	if (entity != null) {
	    		
	    		Parser parser = new Parser();
	    		rate = parser.parseNB(EntityUtils.toString(entity).substring(3));
	    	}
	 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	httpclient.getConnectionManager().shutdown();
	    }
		return rate;
	}
}
