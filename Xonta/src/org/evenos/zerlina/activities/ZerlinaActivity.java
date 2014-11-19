package org.evenos.zerlina.activities;

import org.androidannotations.annotations.EActivity;
import org.evenos.zerlina.utils.ZerlinaApplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.itkamer.xonta.R;

/**
 * Parent Activity which all other Activities in the App extend. It has some general functionality, e. g. a global menu or taking care of
 * logging the user out when the app goes to the background
 * 
 * @author Jan Thielemann
 * @author Eyog Yvon Léonce
 */
@EActivity
public class ZerlinaActivity extends Activity {

	// Booleans to detect if the app goes to the background
	public static boolean isAppWentToBg = false;
	public static boolean isWindowFocused = false;
	public static boolean isMenuOpened = false;
	public static boolean isBackPressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Enable the Back-Button in the ActionBar if the activity is not the Password or the MainActivity
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(!(this instanceof PasswordActivity_|| this instanceof XontaMainActivity_));
		}
	}

	@Override
	protected void onStart() {
		// When the app starts, display the PasswordActivity
		applicationWillEnterForeground();
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// When the app stops, log the user out
		applicationdidenterbackground();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the global menu with Settings, Logout and Change Password
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.global_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Depending on the selected menu entry, display the corresponding activity or go back to the previous activity
		switch (item.getItemId()) {
		//case R.id.action_change_password:
		//	showChangePasswordActivity();
		//	break;
		//case R.id.action_settings:
		//	showSettingsActivity();
		//	break;
		case R.id.action_lockui:
			showEnterPasswordActivity();
			break;
		case android.R.id.home:
			onBackPressed();
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		// Depending on the selected menu entry, display the corresponding activity or go back to the previous activity
		switch (item.getItemId()) {
		//case R.id.action_change_password:
		//	showChangePasswordActivity();
		//	break;
		//case R.id.action_settings:
		//	showSettingsActivity();
		//	break;
		case R.id.action_lockui:
			showEnterPasswordActivity();
			break;
		case android.R.id.home:
			onBackPressed();
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		// If Back-Button was pressed, set the isBackPressed boolean if the Activity is not the PasswordActivity. This is necessary because
		// otherwise, after a Back-press in PassowrdActivity, the app would start again but the user wants to be on his homescreen
		if (!(this instanceof PasswordActivity))
			isBackPressed = true;
		super.onBackPressed();
		overridePendingTransition(R.anim.open_last, R.anim.close_last);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		isWindowFocused = hasFocus;
		if (isBackPressed && !hasFocus) {
			// If the activity has no longer focus and the Back-Button was pressed, everything is fine but if the Back-Button wasn't
			// pressed, this means the app went to the background
			isBackPressed = false;
			isWindowFocused = true;
		}

		super.onWindowFocusChanged(hasFocus);
	}

	//private void showSettingsActivity() {
	//	// Show the SettingsActivity
	//	startActivity(new Intent(this, SettingsActivity_.class));
	//	overridePendingTransition(R.anim.open_next, R.anim.close_next);
	//}

	private void showEnterPasswordActivity() {
		// Logout
		ZerlinaApplication app = (ZerlinaApplication) getApplication();
		app.setPassword(null);
		// Show PasswordActivity
		Intent intent = new Intent(this, PasswordActivity_.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		overridePendingTransition(R.anim.open_last, R.anim.close_last);
		finish();
	}

	//private void showChangePasswordActivity() {
	//	// Show ChangePasswordActivity
	//	startActivity(new Intent(this, ChangePasswordActivity_.class));
	//	overridePendingTransition(R.anim.open_next, R.anim.close_next);
	//}

	private void applicationWillEnterForeground() {
		if (!(this instanceof PasswordActivity)) {
			// If we are not already in the PasswordActivity, check if the app was in the background
			if (isAppWentToBg) {
				isAppWentToBg = false;

				// If App was in the background, display the PasswordActivity and empty the lists
				XontaMainActivity_.contactsList.getContacts().clear();
				
				Intent intent = new Intent(this, PasswordActivity_.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in_no_move, R.anim.fade_out_no_move);
				finish();
			}
		}
	}

	private void applicationdidenterbackground() {
		if (!(this instanceof PasswordActivity)) {
			// If we are not already in the PasswordActivity, check if the app goes to the background
			if (!isWindowFocused) {
				// If the app has no longer focus, that means the app went to the background
				isAppWentToBg = true;

				// Log the user out
				ZerlinaApplication app = (ZerlinaApplication) getApplication();
				app.setPassword(null);
			}
		}
	}

}