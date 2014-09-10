package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import net.sharathkumar.android.apps.knowthyrepresentative.R;
import net.sharathkumar.android.apps.knowthyrepresentative.helpers.AsyncHttpRequest;
import net.sharathkumar.android.apps.knowthyrepresentative.helpers.AsyncZipCodeLocator;
import net.sharathkumar.android.utils.exceptions.RatingsException;
import net.sharathkumar.android.utils.ratings.RateThisApp;
import android.app.Activity;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Exception should be handled in AsyncZipCodeLocator. 
		// Adding an extra layer of try catch, just in case something goes wonky!
		try {
			AsyncZipCodeLocator automaticZipCodeFinder = new AsyncZipCodeLocator(this, R.id.ZipCodeEntered);
			automaticZipCodeFinder.processRequest();
		} catch (Exception err) {
			Log.e("Exception encountered while Autofinding Zip Code.", err.getLocalizedMessage());
		}
		
		rateThisApp();
	}
	
	public void searchForRepresentativeButtonClicked(View buttonClicked) {
		EditText zipCodeEntryField = (EditText) findViewById(R.id.ZipCodeEntered);		
		Editable zipCodeValue = (Editable) zipCodeEntryField.getText();
		
		// Validate Zipcode Entered
		if(zipCodeValue!=null && zipCodeValue.length() == 5) {
			AsyncHttpRequest representativeDataFetcher = new AsyncHttpRequest(this, zipCodeValue.toString());
			representativeDataFetcher.processRequest();
		}
		else {
			zipCodeEntryField.setError("Please enter a valid Zip Code.");
		}
	}
	
	public void rateThisApp() {
	    try {
	        RateThisApp.appLaunched(this, "Know Thy Representative", "net.sharathkumar.android.apps.knowthyrepresentative");
	    } catch (RatingsException err) {
	        Log.e("MainActivity.rateThisApp()", err.getLocalizedMessage());
	    }
	}

}
