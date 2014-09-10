package net.sharathkumar.android.apps.knowthyrepresentative.helpers;

import net.sharathkumar.android.apps.knowthyrepresentative.KnowThyRepresentativeApplication;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GenericHelper {

	private static ConnectivityManager connMgr;
	private static NetworkInfo networkInfo;

	public static boolean isNetworkConnected() {
		boolean returnValue = false;
		
		connMgr = (ConnectivityManager) KnowThyRepresentativeApplication.getContext()
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		
		if(connMgr!=null) {
			networkInfo = connMgr.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				returnValue = true;
			}
		}			
		
		return returnValue;
	}
	
	public static void displayErrorMessage(Activity invokingActivity, String messageToDisplay) {
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
