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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentTemp = inflater.inflate(R.layout.fragment_view_representative_information, container, false);
		
		Representative repObjTemp = (Representative) getArguments().getSerializable(REPRESENTATIVE_INFO);
		if(repObjTemp!=null) {
			
			String districtTemp = repObjTemp.getDistrict();
			String prependText = "Rep. " ;
			if(districtTemp.equalsIgnoreCase("Junior Seat") || districtTemp.equalsIgnoreCase("Senior Seat")) {
				prependText = "Sen. ";
			}
			
			bindImageToField(fragmentTemp, R.id.representative_details_party, repObjTemp.getParty());
			bindValueToField(fragmentTemp, R.id.representative_details_name, prependText + repObjTemp.getName());
//			bindValueToField(fragmentTemp, R.id.representative_details_state, repObjTemp.getState());
//			bindValueToField(fragmentTemp, R.id.representative_details_district, repObjTemp.getDistrict());
			bindPhoneCallToField(fragmentTemp, R.id.representative_details_phone, repObjTemp.getPhone());
			bindOpenWebsiteToField(fragmentTemp, R.id.representative_details_website, repObjTemp.getWebsite());
		}
		
		return fragmentTemp;
	}
	
	public void bindValueToField(View fragmentViewInput, int fieldName, String valueToBind) {
		TextView fieldOnScreenTemp;
		fieldOnScreenTemp = (TextView) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setText(valueToBind);
		}
	}
	
	public void bindImageToField(View fragmentViewInput, int fieldName, String valueToBind) {
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
	
	public void bindPhoneCallToField(View fragmentViewInput, int fieldName, final String valueToBind) {
		Button fieldOnScreenTemp;
		fieldOnScreenTemp = (Button) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
				    Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + valueToBind));
				    startActivity(i);
				  }
				});
		}
	}	
	
	public void bindOpenWebsiteToField(View fragmentViewInput, int fieldName, final String valueToBind) {
		Button fieldOnScreenTemp;
		fieldOnScreenTemp = (Button) fragmentViewInput.findViewById(fieldName);
		if(fieldOnScreenTemp!=null) {
			fieldOnScreenTemp.setOnClickListener(new OnClickListener() {
				  @Override
				  public void onClick(View v) {
				    Uri uri = Uri.parse(valueToBind);
				    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				    startActivity(intent);
				  }
				});
		}
	}
	
}