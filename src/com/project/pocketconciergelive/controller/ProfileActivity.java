package com.project.pocketconciergelive.controller;

import com.project.pocketconciergelive.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ProfileActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.profile);
	}
}
