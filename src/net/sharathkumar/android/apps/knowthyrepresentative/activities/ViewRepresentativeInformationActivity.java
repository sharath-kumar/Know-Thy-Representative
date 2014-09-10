package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import java.util.ArrayList;

import com.viewpagerindicator.CirclePageIndicator;

import net.sharathkumar.android.apps.knowthyrepresentative.R;
import net.sharathkumar.android.apps.knowthyrepresentative.actors.Representative;
import net.sharathkumar.android.apps.knowthyrepresentative.helpers.GenericHelper;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class ViewRepresentativeInformationActivity extends FragmentActivity {
	RepresentativeInfoPageAdapter repInfoPageAdapter;
	CirclePageIndicator circleIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_representative_information);
		ArrayList<RepresentativeInfoFragment> fragments = getFragments();
		repInfoPageAdapter = new RepresentativeInfoPageAdapter(getSupportFragmentManager(), fragments);
		ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(repInfoPageAdapter);
		
//		System.out.println("Indicator -> " + findViewById(R.id.indicator));
		circleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
		circleIndicator.setViewPager(pager);
		
		// Setup New Relic For App Diagnostics
		GenericHelper.setupDiagnostics(this);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<RepresentativeInfoFragment> getFragments() {
		Intent intentTemp = getIntent();
		ArrayList<Representative> listOfRepresentatives = (ArrayList<Representative>) intentTemp.getSerializableExtra("LIST_OF_REPRESENTATIVES");

		ArrayList<RepresentativeInfoFragment> fragList = new ArrayList<RepresentativeInfoFragment>();
		
		for(Representative repObj : listOfRepresentatives) {
			fragList.add(RepresentativeInfoFragment.newInstance(repObj));	
		}
		
		return fragList;
	}

}
