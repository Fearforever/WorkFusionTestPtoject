package rate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import parserXML.Parser;

public class NazBankConnect {
	
	public Float NBGetData(Date date) {
		
		Parser parser = new Parser();
		Float rate = 0f;
    	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	HttpHost target = new HttpHost("www.nbrb.by", 80, "http");
    	String requestString = "/Services/XmlExRates.aspx?ondate=" 
    			+ dateFormat.format(date);
      
    	HttpGet getRequest = new HttpGet(requestString);

		try (CloseableHttpClient httpclient = HttpClientBuilder.create().build()){
	    		 	 
	    	HttpResponse httpResponse = httpclient.execute(target, getRequest);
	    	HttpEntity entity = httpResponse.getEntity();
	 
	    	if (entity != null) {	    		
	    		rate = parser.parseNB(EntityUtils.toString(entity).substring(3));
	    	}
	 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
		return rate;
	}
}
