package com.project.pocketconciergelive.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.project.pocketconciergelive.R;
import com.project.pocketconciergelive.fragment.HotelFragment;
import com.project.pocketconciergelive.fragment.LogInFragment;
import com.project.pocketconciergelive.fragment.RegisterFragment;

public class HotelActivity extends FragmentActivity {

	private Menu menu;
	private HotelFragment hotelFragment;
	private FragmentManager fragmentManager;
	private LogInFragment logInFragment;
	private RegisterFragment registerFragment;
	private UiLifecycleHelper uiHelper;
	private Button login;
	private Button register;
	// Whether has login!
	public static boolean sessionStatu = false;

	private Session.StatusCallback callback = new StatusCallback() {

		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			// TODO Auto-generated method stub
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fragmentManager = getSupportFragmentManager();
		createFragments();
		fragmentManager
				.beginTransaction()
				.add(android.R.id.content, registerFragment, "registerFragment")
				.add(android.R.id.content, logInFragment, "logInFragment")
				.add(android.R.id.content, hotelFragment, "hotelFragment")
				.commit();
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		this.menu = menu;
		// !!!!!!!!
		// menu.getItem(0).setIcon(icon);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.logInprofile) {

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Practice
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}

	// fragment的方法被activity截获
	public void login(View v) {
		// addToBackStack(null) 按返回键可返回上个fragment
		showFragment("LogInFragment", false);
	}

	public void register(View v) {
		showFragment("RegisterFragment", false);
	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	// sessionStateChange 后调用该方法
	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (sessionStatu == true) {
			showFragment("HotelFragment", true);
		} else if (sessionStatu == false) {
			showFragment("HotelFragment", false);
			Toast.makeText(getApplicationContext(), "Success to Logout",
					Toast.LENGTH_SHORT);
		}
		//!!!
		if (state.isClosed()) {
			showFragment("HotelFragment", false);
		}
		if (state.isOpened()) {
			
			
		}
		System.out.println(state.name());
	}

	private void viewChangeBeforeLogIn() {

	}

	private void viewChangeAfterLogIn() {
		login = (Button) findViewById(R.id.loginMain);
		login.setVisibility(Button.INVISIBLE);
		register = (Button) findViewById(R.id.registerMain);
		register.setVisibility(Button.INVISIBLE);
		MenuItem item = menu.getItem(0);
		item.setIcon(getResources().getDrawable(R.drawable.profilelogin));
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						"com.project.pocketconciergelive.controller.ProfileActivity");
				startActivity(intent);
				return true;
			}
		});
	}

	public void createFragments() {
		hotelFragment = new HotelFragment();
		logInFragment = new LogInFragment();
		registerFragment = new RegisterFragment();
	}

	// resultCode show the state of login
	public void showFragment(String fragment, boolean resultCode) {
		sessionStatu = resultCode;
		if (resultCode == true) {
			// UI changed
			viewChangeAfterLogIn();
		}
		if (resultCode == false) {
			viewChangeBeforeLogIn();
		}
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		// hide other fragments, show the target fragment
		if (fragment.equals("RegisterFragment")) {
			fragmentTransaction.show(registerFragment).hide(hotelFragment)
					.hide(logInFragment).addToBackStack("Transaction").commit();
		} else if (fragment.equals("HotelFragment")) {
			// Once LogIn, show hotelFragment without addToBackStack
			fragmentTransaction.show(hotelFragment).hide(logInFragment)
					.hide(registerFragment).commit();
		} else if (fragment.equals("LogInFragment")) {
			fragmentTransaction.show(logInFragment).hide(hotelFragment)
					.hide(registerFragment).addToBackStack("Transaction")
					.commit();
		}
	}

	public boolean getSessionStatu() {
		return sessionStatu;
	}

	public boolean setSessionStatu(boolean statu) {
		return sessionStatu = statu;
	}
}
