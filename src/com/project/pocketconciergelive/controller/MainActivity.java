package com.project.pocketconciergelive.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.project.pocketconciergelive.R;

public class MainActivity extends FragmentActivity {

	private MainFragment mainFragment;
	private FragmentManager fragmentManager;
	private LogInFragment logInFragment;
	private RegisterFragment registerFragment;
	private Bundle savedInstanceState;
	private UiLifecycleHelper uiHelper;

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
		this.savedInstanceState = savedInstanceState;
		fragmentManager = getSupportFragmentManager();

		if (savedInstanceState == null) {
			// Add the fragment on initial activity setup
			mainFragment = new MainFragment();
			logInFragment = new LogInFragment();
			registerFragment = new RegisterFragment();
			// what is fucking android.R.id.content
			fragmentManager.beginTransaction()
					.add(android.R.id.content, mainFragment).commit();
			uiHelper = new UiLifecycleHelper(this, callback);
			uiHelper.onCreate(savedInstanceState);
		} else {
			// Or set the fragment from restored state info
			mainFragment = (MainFragment) getSupportFragmentManager()
					.findFragmentById(android.R.id.content);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		fragmentManager.beginTransaction().addToBackStack(null)
				.add(android.R.id.content, logInFragment).commit();
	}

	public void register(View v) {
		fragmentManager.beginTransaction().addToBackStack(null)
				.add(android.R.id.content, registerFragment).commit();
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
		if (state.isOpened() && savedInstanceState == null) {
			System.out.println("open & saved = null");
		}else if(state.isOpened()){
			Log.i("111", "Logged in...");
			System.out.println("open & saved != null");
			Toast.makeText(getApplicationContext(), "Success!",
					Toast.LENGTH_SHORT).show();
			// Intent intent = new Intent(
			// "com.project.pocketconciergelive.controller.ProfileActivity");
			// fragmentManager.popBackStack();
			// viewChangeAfterLogIn();
			// startActivity(intent);
		} else if (state.isClosed()) {
			Log.i("111", "Logged out...");
		}
	}

	private void viewChangeAfterLogIn() {
		MenuItem item = (MenuItem) findViewById(R.id.logInprofile);
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

	public void showFragment(String fragment) {
		if (fragment.equals("RegisterFragment")) {
			fragmentManager.beginTransaction().addToBackStack(null).replace(android.R.id.content, registerFragment).commit();
		}else{
			fragmentManager.beginTransaction().addToBackStack(null).replace(android.R.id.content, logInFragment).commit();
		}
	}
}
