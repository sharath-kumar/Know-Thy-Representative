package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import net.sharathkumar.android.apps.knowthyrepresentative.R;
import net.sharathkumar.android.apps.knowthyrepresentative.helpers.AsyncHttpRequest;
import net.sharathkumar.android.apps.knowthyrepresentative.helpers.AsyncZipCodeLocator;
import android.app.Activity;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AsyncZipCodeLocator automaticZipCodeFinder = new AsyncZipCodeLocator(this, R.id.ZipCodeEntered);
		automaticZipCodeFinder.processRequest();
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

}
