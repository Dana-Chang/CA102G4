package com.trip.model;

import java.io.*;
import java.net.*;

public class JsonTest {

	public static void main(String[] args) {
		String key = "AIzaSyDHV7GxAc0IWzZQ1bTnAkWlrhI5gdGISsw";
		String searchString = "https://maps.googleapis.com/maps/api/geocode/json?address=中壢車站?&key=" + key + "&callback";

		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;

		try {
			url = new URL(searchString);
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}

	}

}
