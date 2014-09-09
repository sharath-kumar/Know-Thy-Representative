package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import net.sharathkumar.android.apps.knowthyrepresentative.R;
import net.sharathkumar.android.apps.knowthyrepresentative.actors.Representative;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
			bindValueToField(fragmentTemp, R.id.representative_details_name, repObjTemp.getName());
			bindValueToField(fragmentTemp, R.id.representative_details_party, repObjTemp.getParty());
			bindValueToField(fragmentTemp, R.id.representative_details_state, repObjTemp.getState());
			bindValueToField(fragmentTemp, R.id.representative_details_district, repObjTemp.getDistrict());
			bindValueToField(fragmentTemp, R.id.representative_details_phone, repObjTemp.getPhone());
			bindValueToField(fragmentTemp, R.id.representative_details_website, repObjTemp.getWebsite());
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
}