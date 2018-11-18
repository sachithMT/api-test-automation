package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. Get Method
	public void get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		// HTTP get request
		HttpGet httpget = new HttpGet(url);

		// Hit the API ( like send button in POST MAN) and get the response
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

		// Get the response status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code is --------->>" + statusCode);

		// Assign the response into a string variable
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		// Convert the response string into JSON object
		JSONObject jsonObject = new JSONObject(responseString);
		
		System.out.println("Response JSON from API----->"+jsonObject);

		// Get the header details from header response

		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		
		// Create a Hash map

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headersArray) {

			allHeaders.put(header.getName(), header.getValue());
		}
		
		// Print all the header information in Hash map
		System.out.println("Headers Array ------->>"+allHeaders);
	}

}
