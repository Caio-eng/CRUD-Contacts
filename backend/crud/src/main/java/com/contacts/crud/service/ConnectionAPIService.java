package com.contacts.crud.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ConnectionAPIService {
	
	private static HttpsURLConnection connection;
	
	protected ConnectionAPIService() {
	}
	
	protected static HttpsURLConnection getHttpsURLConnection() {
		return connection;
	}

	protected static String connection(String urlParameter) {
		String json = null;

		try {
			URL url = new URL("https://brasilapi.com.br/api/" + urlParameter);

			connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");

			if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
				System.out.println("ERROR. HTTP error code: " + connection.getResponseCode() + "\n");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			String output, retorno = "";

			while ((output = br.readLine()) != null) {
				retorno += output;
			}

			json = retorno;
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}

}
