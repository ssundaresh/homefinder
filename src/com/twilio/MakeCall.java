package com.twilio;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import java.util.Map;
import java.util.HashMap;
 
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;

public class MakeCall {
	private ScheduledExecutorService fScheduler;
	  private long fInitialDelay;
	  private long fDelayBetweenRuns;
	  private long fShutdownAfter;
	  
	final String ACCOUNT_SID = "AC73d57075f8fa0bb42e11e7058c44240f";
    final String AUTH_TOKEN = "3f79e1317e5c3fba6b3cf52d0f634083";
    
	public String returnName(String to_num, String max_price, String num_bedrooms, String callFrequency)
	{
		fInitialDelay = 1;
	    fDelayBetweenRuns = Long.parseLong(callFrequency);
	    fScheduler = Executors.newScheduledThreadPool(1);   
		
			
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", to_num); // Replace with your phone number
        callParams.put("From", "+14152002813"); // Replace with a Twilio number
        callParams.put("Url", "http://homesearch.cloudapp.net/homeFinder/TwimlGenerator?max_price="+max_price+"&num_bedrooms="+num_bedrooms);
        
        Runnable caller = new Caller(callParams);
        if(fDelayBetweenRuns != 0)
        	fScheduler.scheduleWithFixedDelay(caller, fDelayBetweenRuns, fDelayBetweenRuns, TimeUnit.SECONDS);      
        else
        	caller.run();
       writeToFile(callParams);
        return("done");
	    
	}
	
	private void writeToFile(Map<String, String> callParams) {
		try{
    		
    		File file =new File("subscriberslist.txt");
    		System.out.println(file.getAbsolutePath());
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
    		
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file.getName(),true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(callParams.toString());
    	        bufferWritter.close();
    	    	        
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}

	private class Caller implements Runnable {
		
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
		private Map<String, String> callParams;
        
		public Caller(Map<String, String> callParams) {
			this.callParams = callParams;
		}

		@Override
		public void run() {
			 // Make the call
	        Call call = null;
			try {
				call = callFactory.create(callParams);
			} catch (TwilioRestException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
