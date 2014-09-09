package net.sharathkumar.android.apps.knowthyrepresentative.activities;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RepresentativeInfoPageAdapter extends FragmentPagerAdapter {
	private ArrayList<RepresentativeInfoFragment> fragments;

	public RepresentativeInfoPageAdapter(FragmentManager fm, ArrayList<RepresentativeInfoFragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
}
