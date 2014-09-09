package net.sharathkumar.android.apps.knowthyrepresentative.actors;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Representative implements RepresentativeAttributes, Serializable {
	
	private String name;
	private String party;
	private String state;
	private String district;
	private String phone;
	private String office;
	private String website;
	
	public Representative(JSONObject jsonInput) {
		try {
			// Too much paranoia?
			name = (jsonInput.getString(TAG_NAME)!=null) ? (jsonInput.getString(TAG_NAME)) : "" ;
			party = (jsonInput.getString(TAG_PARTY)!=null) ? (jsonInput.getString(TAG_PARTY)) : "" ;
			state = (jsonInput.getString(TAG_STATE)!=null) ? (jsonInput.getString(TAG_STATE)) : "" ;
			district = (jsonInput.getString(TAG_DISTRICT)!=null) ? (jsonInput.getString(TAG_DISTRICT)) : "" ;
			phone = (jsonInput.getString(TAG_PHONE)!=null) ? (jsonInput.getString(TAG_PHONE)) : "" ;
			office = (jsonInput.getString(TAG_OFFICE)!=null) ? (jsonInput.getString(TAG_OFFICE)) : "" ;
			website = (jsonInput.getString(TAG_WEBSITE)!=null) ? (jsonInput.getString(TAG_WEBSITE)) : "" ;
		} catch (JSONException err) {				
			Log.e("Representative()", err.getLocalizedMessage());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getParty() {
		return party;
	}

	@Override
	public void setParty(String party) {
		this.party = party;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String toString() {
		StringBuffer returnValue = new StringBuffer();		
		String SEPARATOR = "; ";
		
		returnValue.append("(");
		returnValue.append("name: " + getName() + SEPARATOR);
		returnValue.append("state: " + getState() + SEPARATOR);
		returnValue.append("district: " + getDistrict() + SEPARATOR);
		returnValue.append("phone: " + getPhone() + SEPARATOR);
		returnValue.append("office: " + getOffice() + SEPARATOR);
		returnValue.append("website: " + getWebsite() + SEPARATOR);
		returnValue.append(")");
		
		
		return returnValue.toString();
	}
	
	public String toJSON() {
		StringBuffer returnValue = new StringBuffer();
		String SEPARATOR = ", ";
		String PRE = "\"";
		String POST = "\"";
		
		returnValue.append("{");
		returnValue.append("name: " + PRE + getName() + POST + SEPARATOR);
		returnValue.append("state: " + PRE + getState() + POST + SEPARATOR);
		returnValue.append("district: " + PRE + getDistrict() + POST + SEPARATOR);
		returnValue.append("phone: " + PRE + getPhone() + POST + SEPARATOR);
		returnValue.append("office: " + PRE + getOffice() + POST + SEPARATOR);
		returnValue.append("website" + PRE + getWebsite() + POST + SEPARATOR);
		returnValue.append("}");
		
		return returnValue.toString();
	}
	
}
