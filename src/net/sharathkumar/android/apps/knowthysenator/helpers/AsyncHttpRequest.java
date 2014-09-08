package net.sharathkumar.android.apps.knowthysenator.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import net.sharathkumar.android.apps.knowthysenator.KnowThySenatorApplicationContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncHttpRequest extends AsyncTask<String, Void, String> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outputOfHttpRequest = "";
	private String UrlToQuery = "http://whoismyrepresentative.com/getall_mems.php?output=json&zip=";
	
	public AsyncHttpRequest(String inputZipCode) {
		UrlToQuery+=inputZipCode;
	}
	
	public void processRequest() {
		execute(UrlToQuery);
	}
	
	public String getOutput() {
		return outputOfHttpRequest;
	}

	public void setOutput(String outputOfHttpRequest) {
		this.outputOfHttpRequest = outputOfHttpRequest;
	}

	public static String GET(String url){
        InputStream inputStream = null;
        StringBuffer returnValue = new StringBuffer();
        try {
 
        	HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            
            if(inputStream != null)
            	returnValue.append(convertInputStreamToString(inputStream));
            else
            	returnValue.append("Unable To Retrieve Data!");
 
        } catch (Exception err) {
            Log.e("InputStream", err.getLocalizedMessage());
        }
 
        return returnValue.toString();
    }
 
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
 
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) KnowThySenatorApplicationContext.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }

	protected String doInBackground(String... urls) {
		return GET(urls[0]);
	}

	protected void onPostExecute(String result) {
		ResultsParser.parseJson(result);
		setOutput(result);
        Log.d("AsyncHttpRequest.onPostExecute() :: result", result);
   }

}
