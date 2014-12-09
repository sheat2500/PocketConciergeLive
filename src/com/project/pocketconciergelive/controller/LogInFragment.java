package com.project.pocketconciergelive.controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.LoginButton;
import com.project.pocketconcierge.database.DBAdapter;
import com.project.pocketconciergelive.R;

public class LogInFragment extends Fragment {

	private static final String LOGINFRAGMENT = "LogInFragment";
	TextView username;
	TextView password;
	TextView showview;
	DBAdapter dbAdapter;
	private LoginButton loginButton;
	private Button login;
	private Button register;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_login, container, false);
		username = (TextView) v.findViewById(R.id.username);
		password = (TextView) v.findViewById(R.id.password);
		showview = (TextView) v.findViewById(R.id.showView);
		loginButton = (LoginButton) v.findViewById(R.id.authButton);
		dbAdapter = new DBAdapter(getActivity());
		login = (Button) v.findViewById(R.id.login);
		// login Action
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Default Admin Account & Database Accounts
				if (username.getText().toString().equals("admin")
						&& password.getText().toString().equals("admin")
						|| validateAccount(username.getText().toString(),
								password.getText().toString())) {
					Toast.makeText(getActivity(), "Success!",
							Toast.LENGTH_SHORT).show();
					showview.setText("Success!");
					
					
					
					// Intent intent = new Intent(
					// "com.project.pocketconciergelive.controller.ProfileActivity");
					// startActivity(intent);
				} else {
					showview.setText("Try Again!");
				}
			}
		});
		register = (Button) v.findViewById(R.id.register);

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity mainActivity = (MainActivity) getActivity();
				mainActivity.showFragment("RegisterFragment");
			}
		});
		return v;
	}

	// Validate User's Input
	private boolean validateInput() {
		return true;
	}
	
	// Now, local datebase works. Futher development should connect to the webserver. 
	// Also, jump to the MainFragment with Profile Information got from webserver
	private boolean validateAccount(String username, String password) {
		dbAdapter.open();
		Cursor cursor = dbAdapter.getAccount(username, password);
		if(cursor.getCount()==0){
			dbAdapter.close();
			return false;
		}
		dbAdapter.close();
		return true;
	}
}
