package net.sharathkumar.android.apps.knowthyrepresentative.helpers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import net.sharathkumar.android.apps.knowthyrepresentative.R;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;

public class AsyncZipCodeLocator extends AsyncTask<String, Integer, String> {

	String zipCode;
	Activity invokingActivity;
	int fieldIdOnInvokingActivityUIXToUpdate ;
	
	public AsyncZipCodeLocator(Activity invokingActivityInput, int fieldToUpdateInput) {
		invokingActivity = invokingActivityInput;
		fieldIdOnInvokingActivityUIXToUpdate = fieldToUpdateInput;
	}
	
	public void processRequest() {
		Log.d("AsyncZipCodeLocator.processRequest()", "Entered");
		execute();
	}
	
	public String getZipCode() {
		if(this.zipCode != null)
			return this.zipCode;
		else 
			return "";
	}

	public void setZipCode(String zipCodeInput) {
		if(zipCodeInput!=null && zipCodeInput.length()>0) {
			this.zipCode = zipCodeInput;	
		}
	}

	public String findZipCodeBasedOnLocation() {
		Log.d("AsyncZipCodeLocator.findZipCodeBasedOnLocation()", "Entered");
		
		String returnValue = "";
	    
		LocationManager locationMgr = (LocationManager) invokingActivity.getSystemService(Context.LOCATION_SERVICE);
	    double longitude = 0.0;
	    double latitude = 0.0;
	    
	    Criteria criteria  = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_COARSE);
	    criteria.setAltitudeRequired(false);
	    criteria.setBearingRequired(false);
	    criteria.setCostAllowed(true);
	    criteria.setPowerRequirement(Criteria.POWER_LOW);

	    String provider = locationMgr.getBestProvider(criteria, true);
	    Location location = locationMgr.getLastKnownLocation(provider);
	    
	    if(location != null) {
	        longitude = location.getLongitude();
	        latitude = location.getLatitude();
	    }
	    
	    try {
	    	Geocoder geocoder = new Geocoder(invokingActivity, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			if(addresses!=null && addresses.size()>0) {
				zipCode = addresses.get(0).getPostalCode();
				setZipCode(zipCode) ;
			}
		} catch (IOException err) {
			Log.e("AsyncZipCodeLocator.findZipCodeBasedOnLocation()", err.getLocalizedMessage());
		}

	    Log.d("Best available provider", provider);
	    Log.d("(LAT, LON, ZIP)", "(" + latitude + ", " + longitude + ", " +  zipCode + ")");
	    
	    return returnValue;
	}
	
	protected String doInBackground(String... urls) {
		String zipCodeFound = "" ;
		try {
			zipCodeFound = findZipCodeBasedOnLocation();
		} catch(Exception err) {
			Log.e("AsyncZipCodeLocator.doInBackground()", err.getLocalizedMessage());
		}
		
		return zipCodeFound;
	}

	protected void onPostExecute(String result) {
		Log.d("AsyncZipCodeLocator.onPostExecute()", "Entered");
        
        EditText zipCodeEntryField = (EditText) invokingActivity.findViewById(R.id.ZipCodeEntered);	
		Editable zipCodeValue = (Editable) zipCodeEntryField.getText();
		
		Log.d("AsyncZipCodeLocator.onPostExecute() :: ZipCode", getZipCode());
		
		if(zipCodeValue!=null && zipCodeValue.length() == 0) {
			zipCodeEntryField.setText(getZipCode());
		}
		
   }

}
