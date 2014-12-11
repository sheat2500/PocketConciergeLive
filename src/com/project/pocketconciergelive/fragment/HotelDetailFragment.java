package com.project.pocketconciergelive.fragment;

import com.project.pocketconciergelive.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * In the ProfileActivity
 * 
 * @author Te
 * 
 */
public class HotelDetailFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_hoteldetail, container, false);
		return v;
	}
}
