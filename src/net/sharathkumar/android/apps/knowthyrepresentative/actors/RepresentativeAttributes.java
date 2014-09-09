package net.sharathkumar.android.apps.knowthyrepresentative.actors;

public interface RepresentativeAttributes {
	// JSON Node names
	static final String TAG_NAME = "name";
	static final String TAG_PARTY = "party";
	static final String TAG_STATE = "state";
	static final String TAG_DISTRICT = "district";
	static final String TAG_PHONE = "phone";
	static final String TAG_OFFICE = "office";
	static final String TAG_WEBSITE = "link";

	public String getName();
	public void setName(String name);
	public String getParty() ;
	public void setParty(String party) ;
	public String getState();
	public void setState(String state);
	public String getDistrict();
	public void setDistrict(String district);
	public String getPhone() ;
	public void setPhone(String phone) ;
	public String getOffice() ;
	public void setOffice(String office) ;
	public String getWebsite() ;
	public void setWebsite(String website) ;
}
