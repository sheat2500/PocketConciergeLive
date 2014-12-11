package com.project.pocketconciergelive.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.project.pocketconciergelive.R;
import com.project.pocketconciergelive.fragment.HotelDetailFragment;
import com.project.pocketconciergelive.fragment.ProfileFragment;

/**
 * ProfileActivity: 1) HotelDetailFragment, 2) ProfileFragment
 * 
 * @author Te
 * 
 */
public class ProfileActivity extends FragmentActivity {

	public static final int HOTELDETAIL = 0;
	public static final int PROFILE = 1;
	public static final int FRAGMENT_COUNT = PROFILE + 1;
	public Fragment[] fragments = new Fragment[FRAGMENT_COUNT];

	HotelDetailFragment hotelDetailFragment;
	ProfileFragment profileFragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_profile);
		// Get Fragment from layout using FragmentManager
		FragmentManager fm = getSupportFragmentManager();
		profileFragment = (ProfileFragment) fm
				.findFragmentById(R.id.profilefragment);
		hotelDetailFragment = (HotelDetailFragment) fm
				.findFragmentById(R.id.hoteldetailfragment);
		fragments[PROFILE] = profileFragment;
		fragments[HOTELDETAIL] = hotelDetailFragment;
		FragmentTransaction transaction = fm.beginTransaction();
		for (int i = 0; i < fragments.length; i++) {
			transaction.hide(fragments[i]);
		}
		transaction.show(fragments[HOTELDETAIL]);
		transaction.commit();
	}

	public void showFragment(int fragment) {
		// getFragmentManager & getSupportFragmentManager
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.show(profileFragment);
	}
}
