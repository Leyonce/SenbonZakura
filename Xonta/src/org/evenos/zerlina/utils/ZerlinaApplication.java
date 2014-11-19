package org.evenos.zerlina.utils;

import net.sqlcipher.database.SQLiteDatabase;

import org.androidannotations.annotations.EApplication;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Application class for this app, used to initialize SQLCipher and ActiveAndroid
 * 
 * @author Jan Thielemann
 * @author Eyog Yvon Léonce
 */
@EApplication
public class ZerlinaApplication extends Application {

	private boolean activeAndroidInitialized = false;

	public void setPassword(String password) {
		if (password != null) {
			// If the password is not null, try to initialize ActiveAndroid with the password
			ActiveAndroid.initialize(this, password);
			activeAndroidInitialized = true;
		} else {
			// If the password is null, cancel all running requests
			//if (WebServiceUtils.getInstance().isExecuting())
			//	WebServiceUtils.getInstance().cancelRequests();

			// If ActiveAndroid is initialized, dispose it
			if (activeAndroidInitialized) {
				ActiveAndroid.dispose();
				activeAndroidInitialized = false;
			}
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// When the app is created, load the libraries for SQLCipher
		SQLiteDatabase.loadLibs(this);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		// When the app is terminated, tear down ActiveAndroid
		setPassword(null);
	}
}
