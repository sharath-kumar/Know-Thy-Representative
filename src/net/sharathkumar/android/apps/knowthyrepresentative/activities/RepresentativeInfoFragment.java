package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import net.sharathkumar.android.apps.knowthyrepresentative.R;
import net.sharathkumar.android.apps.knowthyrepresentative.actors.Representative;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RepresentativeInfoFragment extends Fragment {
	
	public static final String REPRESENTATIVE_INFO = "REPRESENTATIVE_INFO";
	
	public static final RepresentativeInfoFragment newInstance(Representative repData) {
		RepresentativeInfoFragment repInfo = new RepresentativeInfoFragment();
		Bundle bdl = new Bundle();
		bdl.putSerializable("REPRESENTATIVE_INFO", repData);
		repInfo.setArguments(bdl);
		return repInfo;
	}	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentTemp = inflater.inflate(R.layout.fragment_view_representative_information, container, false);
		
		Representative repObjTemp = (Representative) getArguments().getSerializable(REPRESENTATIVE_INFO);
		if(repObjTemp!=null) {
			bindPartyImage(fragmentTemp, R.id.representative_details_party, repObjTemp.getParty());
			bindName(fragmentTemp, R.id.representative_details_name, repObjTemp.getDistrict(), repObjTemp.getName());
			bindPhoneCall(fragmentTemp, R.id.representative_details_phone, repObjTemp.getPhone());
			bindOpenWebsite(fragmentTemp, R.id.representative_details_website, repObjTemp.getWebsite());
			bindShareButton(fragmentTemp, repObjTemp, R.id.share_with_friends);
		}
		
		return fragmentTemp;		
	}
	
	public void bindName(View fragmentViewInput, int fieldName, String districtInput, String nameInput) {
		TextView fieldOnScreenTemp;
		
		fieldOnScreenTemp = (TextView) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setText(formattedNameToBeDisplayOnScreen(districtInput, nameInput));
		}
	}
	
	public String formattedNameToBeDisplayOnScreen(String districtInput, String nameInput) {
		String returnValue = "";
		
		String prependText = "Rep. " ;
		if(districtInput.equalsIgnoreCase("Junior Seat") || districtInput.equalsIgnoreCase("Senior Seat")) {
			prependText = "Sen. ";
		}
		
		returnValue = prependText + nameInput;
		
		return returnValue;
	}
	
	public void bindPartyImage(View fragmentViewInput, int fieldName, String valueToBind) {
		ImageView fieldOnScreenTemp;
		
		fieldOnScreenTemp = (ImageView) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			if(valueToBind.equalsIgnoreCase("R")) {
				fieldOnScreenTemp.setImageResource(R.drawable.republicanlogo_icon);
			}
			else {
				fieldOnScreenTemp.setImageResource(R.drawable.democraticlogo_icon);
			}
		}
	}
	
	public void bindPhoneCall(View fragmentViewInput, int fieldName, final String valueToBind) {
		Button fieldOnScreenTemp;
		fieldOnScreenTemp = (Button) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setOnClickListener(new OnClickListener() {
				  public void onClick(View v) {
				    Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + valueToBind));
				    startActivity(i);
				  }
				});
		}
	}	
	
	public void bindOpenWebsite(View fragmentViewInput, int fieldName, final String valueToBind) {
		Button fieldOnScreenTemp;
		fieldOnScreenTemp = (Button) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setOnClickListener(new OnClickListener() {
				  public void onClick(View v) {
				    Uri uri = Uri.parse(valueToBind);
				    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				    startActivity(intent);
				  }
				});
		}
	}
	
	public void bindShareButton(View fragmentViewInput, final Representative repObj, int fieldName) {
		Button fieldOnScreenTemp;
		fieldOnScreenTemp = (Button) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setOnClickListener(new OnClickListener() {
				  public void onClick(View v) {
					//create the send intent
					Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);

					//set the type
					shareIntent.setType("text/plain");

					//build the body of the message to be shared
					String shareMessage = "Contact "  + formattedNameToBeDisplayOnScreen(repObj.getParty(), repObj.getName() 
							  							+ "\n at " + repObj.getPhone() 
							  							+ "\n or " + repObj.getWebsite());

					//add the message
					shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);

					//start the chooser for sharing
					startActivity(Intent.createChooser(shareIntent, "Rally Support For Your Cause!"));
				  }
				});
		}
	}	
}