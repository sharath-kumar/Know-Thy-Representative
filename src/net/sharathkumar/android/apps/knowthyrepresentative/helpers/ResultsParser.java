package net.sharathkumar.android.apps.knowthyrepresentative.helpers;

import java.util.ArrayList;
import net.sharathkumar.android.apps.knowthyrepresentative.actors.Representative;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ResultsParser {
	
	public static ArrayList<Representative> parseJson(String dataToParse) {
		ArrayList<Representative> returnValue = new ArrayList<Representative>();
		
		try {
			
			JSONObject resultsJson = new JSONObject(dataToParse);
			JSONArray represenatives = resultsJson.getJSONArray("results");
			
			for(int i=0; i<represenatives.length(); i++) {
				JSONObject nodeTemp = represenatives.getJSONObject(i);
				returnValue.add(new Representative(nodeTemp));				
			}
			
		} catch (JSONException err) {
			Log.e("ResultsParser.parseJson()", err.getLocalizedMessage());
		}
				
		return returnValue;
	}

}
