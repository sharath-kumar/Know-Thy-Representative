package net.sharathkumar.android.apps.knowthysenator.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import net.sharathkumar.android.apps.knowthysenator.R;
import net.sharathkumar.android.apps.knowthysenator.helpers.AsyncHttpRequest;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Handler mHandler = new Handler(Looper.getMainLooper()) ;
		
	}
	
	public void searchForSenator(View buttonClicked) {
		EditText zipCodeEntryField = (EditText) findViewById(R.id.ZipCodeEntered);		
		Editable zipCodeValue = (Editable) zipCodeEntryField.getText();
		
		if(zipCodeValue!=null && zipCodeValue.length() == 5) {
			AsyncHttpRequest representativeDataFetcher = new AsyncHttpRequest(zipCodeValue.toString());
			representativeDataFetcher.processRequest();
			
			Intent displayResultsPageIntent = new Intent(this, ViewSenatorInformation.class) ;
			displayResultsPageIntent.putExtra("DATA_FETCHER", representativeDataFetcher);
			startActivity(displayResultsPageIntent) ;
		}
		else {
			zipCodeEntryField.setError("Please enter a valid Zip Code.");
		}
	}
}
