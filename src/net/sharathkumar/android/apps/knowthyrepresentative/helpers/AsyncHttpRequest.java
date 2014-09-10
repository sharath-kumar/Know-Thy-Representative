package net.sharathkumar.android.apps.knowthyrepresentative.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import net.sharathkumar.android.apps.knowthyrepresentative.KnowThySenatorApplication;
import net.sharathkumar.android.apps.knowthyrepresentative.activities.ViewRepresentativeInformationActivity;
import net.sharathkumar.android.apps.knowthyrepresentative.actors.Representative;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class AsyncHttpRequest extends AsyncTask<String, Void, String> implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Representative> listOfRepresentatives = new ArrayList<Representative>();
	private String UrlToQuery = "http://whoismyrepresentative.com/getall_mems.php?output=json&zip=";
	private Activity invokingActivity;
	
	public AsyncHttpRequest(Activity invokingActivityInput, String inputZipCode) {
		invokingActivity = invokingActivityInput;
		UrlToQuery+=inputZipCode;
	}
	
	public void processRequest() {
		execute(UrlToQuery);
	}

	public String GET(String url) {
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
        ConnectivityManager connMgr = (ConnectivityManager) KnowThySenatorApplication.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }

	protected String doInBackground(String... urls) {
		
		if(isConnected()) {
			return GET(urls[0]);	
		}
		else {
			return "";
		}
		
	}

	protected void onPostExecute(String result) {
        Log.d("AsyncHttpRequest.onPostExecute() :: result", result);
		
		if(result!=null && result.length()>0) {	
			try {
				
				listOfRepresentatives = ResultsParser.parseJson(result);
				Intent displayResultsPageIntent = new Intent(invokingActivity, ViewRepresentativeInformationActivity.class) ;
				displayResultsPageIntent.putExtra("LIST_OF_REPRESENTATIVES", listOfRepresentatives) ;
				invokingActivity.startActivity(displayResultsPageIntent) ;			
				
			} catch(JSONException err) {
				displayErrorMessage("Unable to retrieve data." +
									"\n\nPlease try a different Zipcode!");
				
			} catch(Exception err) {
				displayErrorMessage("Something went terribly wrong." +
									"\n\nPlease try again in a few minutes!");
			}
					
		}
		else {
			displayErrorMessage("No Internet Connectivity." +
								"\n\nPlease enable internet connectivity and try again!");
		}
   }
	
	public void displayErrorMessage(String messageToDisplay) {
		Log.d("AsyncHttpRequest.displayErrorMessage() :: messageToDisplay", messageToDisplay);
		
        AlertDialog.Builder errorAlertMessage  = new AlertDialog.Builder(invokingActivity);

        errorAlertMessage.setMessage(messageToDisplay);
        errorAlertMessage.setTitle("Ooopsie!!!");
        errorAlertMessage.setPositiveButton("OK", null);
        errorAlertMessage.setCancelable(true);
        errorAlertMessage.create().show();

        errorAlertMessage.setPositiveButton("Ok",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	// For now, do nothing!
            }
        });
	}

}
