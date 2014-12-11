package com.project.pocketconciergelive.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.pocketconcierge.database.DBAdapter;
import com.project.pocketconciergelive.R;

public class RegisterFragment extends Fragment {

	TextView username;
	TextView password;
	TextView showview;
	TextView repassword;
	TextView datebirth;
	Button submit;
	DBAdapter dbAdapter;
	AlertDialog.Builder alertBuilder;
	android.content.DialogInterface.OnClickListener positiveClickListener;
	android.content.DialogInterface.OnClickListener negitiveClickListener;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_register, container, false);
		username = (TextView) v.findViewById(R.id.username);
		password = (TextView) v.findViewById(R.id.password);
		repassword = (TextView) v.findViewById(R.id.repassword);
		showview = (TextView) v.findViewById(R.id.showView);
		datebirth = (TextView) v.findViewById(R.id.datebirth);
		dbAdapter = new DBAdapter(getActivity());
		alertBuilder = new AlertDialog.Builder(getActivity());
		submit = (Button) v.findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Unfinished validate users input
				invalidate();
				Bundle bundle = new Bundle();
				bundle.putString("username", username.getText().toString());
				bundle.putString("password", password.getText().toString());
				bundle.putString("datebirth", datebirth.getText().toString());
				positiveClickListener = new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				};
				
				negitiveClickListener = new android.content.DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				};
				
				// ?????????????????????execute
				// Connect to WebServer
				// new RegisterSuccess().execute(bundle);
				boolean flag = register(bundle);
				if (flag == true) {
					alertBuilder.setTitle("Note").setMessage(
							"Register Succeess!").setPositiveButton("ok", positiveClickListener);
				}

			}
		});
		return v;
	}

	// validate all parameters users' input
	private void invalidate() {

	}

	private boolean register(Bundle bundle) {
		dbAdapter.open();
		boolean flag = dbAdapter.insertAccount(bundle.getString("username"),
				bundle.getString("password"), bundle.getString("datebirth"));
		dbAdapter.close();
		return flag;
	}

	class RegisterSuccess extends AsyncTask<Bundle, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(Bundle... params) {
			// TODO Auto-generated method stub
			Bundle bundle = params[0];
			dbAdapter.open();
			dbAdapter
					.insertAccount(bundle.getString("username"),
							bundle.getString("password"),
							bundle.getString("datebirth"));
			dbAdapter.close();
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

	}
}
