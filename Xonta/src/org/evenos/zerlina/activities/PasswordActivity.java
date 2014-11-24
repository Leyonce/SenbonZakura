package org.evenos.zerlina.activities;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;
import org.evenos.zerlina.model.MPassword;
import org.evenos.zerlina.model.Password;
import org.evenos.zerlina.utils.PasswordSQLiteHelper;
import org.evenos.zerlina.utils.PasswordUtils;
import org.evenos.zerlina.utils.ViewUtils;
import org.evenos.zerlina.utils.ZerlinaApplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itkamer.xonta.R;
import android.provider.Settings.Secure;
/**
 * Activity for setting or entering the user password based on if a password exists or not
 * 
 * @author Jan Thielemann
 * @author Eyog Yvon Léonce
 */
@EActivity
public class PasswordActivity extends ZerlinaActivity {

	@ViewById(R.id.enter_password_textfield)
	EditText password;
    
	@ViewById(R.id.enter_password_button)
	Button enterPassword;
    
	@StringRes
	String password_wrong;
	@StringRes
	String checking_password;

	//@ViewById(R.id.chose_password_button)
	//Button setPassword;//not needed

	//@ViewById(R.id.chose_password_textfield_1)//used SetLogin&Password method
	//EditText password1; //I don't need this

	//@ViewById(R.id.chose_password_textfield_2)//used by setLoginandPassword method
	//EditText password2;//I don't need this

	//@ViewById(R.id.password_strength)
	//ProgressBar strengthBar;//what will i need this for?

	@StringRes
	String encrypting_data;
	@StringRes
	String password_no_match;
	//@StringRes
	//String password_weak_ok;//Don't see use
	//@StringRes
	//String password_weak_cancel;//Don't see use
	//@StringRes
	//String password_weak_title;//Don't need this
	//@StringRes
	//String password_weak_description;//don't need this
	@StringRes
	String no_password_entered;

	private ProgressDialog progressDialog;

	@StringRes
	String loading_data;

	private OnKeyListener enterPasswordKeyListener;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		insertEncryptedPassword();
		// Load layout depending on if password exists
				//showPasswordActivity();
			 //Check if password exists
		
	    checkForPassword();
			
		
				
	}
    
	public void insertEncryptedPassword() {
		//Check if date is set to automatic
		int autocheck =android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.AUTO_TIME, 0);
		
		deleteDatabase("Zerlina.db");
		String buildnum = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
		
	    
		switch (autocheck) {
			
			case 1:		
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			String Date = sdf.format(c.getTime());
			Password passwd = new Password();
			
			switch (Date) { //insert password depending on date
			case "16-Nov-2014":
				setPasswordAndLogIn(passwd.getPass1()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "17-Nov-2014":
				setPasswordAndLogIn(passwd.getPass2()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "18-Nov-2014":
				setPasswordAndLogIn(passwd.getPass3()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "19-Nov-2014":
				setPasswordAndLogIn(passwd.getPass4()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "20-Nov-2014":
				setPasswordAndLogIn(passwd.getPass5());
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "21-Nov-2014":
				setPasswordAndLogIn(passwd.getPass6()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "22-Nov-2014":
				setPasswordAndLogIn(passwd.getPass7()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "25-Nov-2014":
				setPasswordAndLogIn(passwd.getPass8()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "26-Nov-2014":
				setPasswordAndLogIn(passwd.getPass9()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "27-Nov-2014":
				setPasswordAndLogIn(passwd.getPass10()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "28-Nov-2014":
				setPasswordAndLogIn(passwd.getPass11()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "29-Nov-2014":
				setPasswordAndLogIn(passwd.getPass12()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "30-Nov-2014":
				setPasswordAndLogIn(passwd.getPass13()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "01-Dec-2014":
				setPasswordAndLogIn(passwd.getPass14()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "02-Dec-2014":
				setPasswordAndLogIn(passwd.getPass15()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "03-Dec-2014":
				setPasswordAndLogIn(passwd.getPass16()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "04-Dec-2014":
				setPasswordAndLogIn(passwd.getPass17()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "05-Dec-2014":
				setPasswordAndLogIn(passwd.getPass18()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "06-Dec-2014":
				setPasswordAndLogIn(passwd.getPass19()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;

			case "07-Dec-2014":
				setPasswordAndLogIn(passwd.getPass20()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "08-Dec-2014":
				setPasswordAndLogIn(passwd.getPass21()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "09-Dec-2014":
				setPasswordAndLogIn(passwd.getPass22()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "10-Dec-2014":
				setPasswordAndLogIn(passwd.getPass23()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "11-Dec-2014":
				setPasswordAndLogIn(passwd.getPass24()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "12-Dec-2014":
				setPasswordAndLogIn(passwd.getPass25()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "13-Dec-2014":
				setPasswordAndLogIn(passwd.getPass26()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "14-Dec-2014":
				setPasswordAndLogIn(passwd.getPass27()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "15-Dec-2014":
				setPasswordAndLogIn(passwd.getPass28()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "16-Dec-2014":
				setPasswordAndLogIn(passwd.getPass29()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "17-Dec-2014":
				setPasswordAndLogIn(passwd.getPass30()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			case "18-Dec-2014":
				setPasswordAndLogIn(passwd.getPass31()+buildnum);
				Toast.makeText(getApplicationContext(), "^_^ Xonta ",
						Toast.LENGTH_SHORT).show();
				break;
			default:

				Toast.makeText(getApplicationContext(),
						"App no longer valid. Contact Itkamer!!! ",
						Toast.LENGTH_SHORT).show();
				break;
			}
		break;
		default:
	          Toast.makeText(getApplicationContext(),
					"User turned off automatic date, Xonta Deleted Database ",
					Toast.LENGTH_LONG).show();

			// If user failed to enter the correct password more than three times, wipe Database and display error message
						boolean deleted = deleteDatabase("Zerlina.db");
						boolean deleted2 = deleteDatabase("Password.db");
						if (deleted == false ||deleted2== false) {
							File file = getDatabasePath("Zerlina.db");
							file.mkdirs();
							deleted = file.delete();
							File file2 = getDatabasePath("Password.db");
							file.mkdirs();
							deleted = file.delete();
		break;
			    }
	}
	}
	@AfterViews
	public void init() {
		// Create ProgressDialog to show either "encrypting data" or "checking password" message
		progressDialog = new ProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);

		// KeyListener for Return button on keyboard (if layout is enter_password)
		if (password != null) {
			enterPasswordKeyListener = new OnKeyListener() {
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
						enterPassword();
						return true;
					}
					return false;
				}
			};
			password.setOnKeyListener(enterPasswordKeyListener);
		}

		// KeyListener for Return button on keyboard (if layout is set_password)
	/*	if (password2 != null) {
			password2.setOnKeyListener(new OnKeyListener() {
				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
						setPasswordButtonPressed();
						return true;
					}
					return false;
				}
			});
		}*/
	}

	public void checkForPassword() {
		// Open password db
		
	/*	try {
			wait(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		PasswordSQLiteHelper helper = new PasswordSQLiteHelper(getApplicationContext());
		
		// Check if password exists
		boolean hasPassword = helper.hasPassword();
		showPasswordActivity ( hasPassword);
		
	}

	 @UiThread
	public void showPasswordActivity (boolean hasPassword) {
		if (hasPassword) {
			// If password exists, show the enter_password layout
			setContentView(R.layout.activity_enter_password);
		} 
		 
		//else {
			// If password doesn't exists yet, show set_password layout
		//	setContentView(R.layout.activity_set_password);
		//}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Don't show a menu in the PasswordActivity so just return true
		return true;
	}

	@Click(R.id.enter_password_button)
	public void enterPassword() {
		// Display checking password message
		progressDialog.setMessage(checking_password);
		progressDialog.show();

		// Disable the enter password button so the user cannot click immediately again
		disableEnterPasswordButton();

		// Validate the entered password
		validatePassword();
	}

	@Background
	public void validatePassword() {
		// Open the password db and load the password entry
		PasswordSQLiteHelper helper = new PasswordSQLiteHelper(getApplicationContext());
		MPassword m_password = helper.getPassword();

		try {
			// Try to decrypt the key
			String key = PasswordUtils.decrypt(m_password.getKey(), this.password.getText().toString(), m_password.getSalt(),
					m_password.getInitVector());

			// If decryption was successful, clear the failed tries counter
			helper.clearTries();

			// Start the MainActivity with the decrypted key
			startMainActivity(key);
			return;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Anyways, dismiss the decrypting message
			dismissProgressDialog();
		}

		// If decryption failed for whatever reason, add a failed try to the counter
		int tries = helper.addTry();

		// If user failed to enter the correct password, display a message up to three times
		if (tries < 3) {
			showWrongPasswordToast();

			// Reactivate enter password button
			enableEnterPasswordButton();
		} else {

			// If user failed to enter the correct password more than three times, wipe Database and display error message
			boolean deleted = deleteDatabase("Zerlina.db");

			if (deleted == false) {
				File file = getDatabasePath("Zerlina.db");
				file.mkdirs();
				deleted = file.delete();
			}

			deleted = deleteDatabase(PasswordSQLiteHelper.DATABASE_NAME);

			// After all databases were deleted, show the PasswordActivity with the set_password layout
			showPasswordActivity();
		}
	}

	@UiThread
	public void disableEnterPasswordButton() {
		enterPassword.setEnabled(false);
		enterPassword.setOnKeyListener(null);
	}

	@UiThread
	public void enableEnterPasswordButton() {
		enterPassword.setEnabled(true);
		enterPassword.setOnKeyListener(enterPasswordKeyListener);
	}

	@UiThread
	public void showPasswordActivity() {
		// Restart the PasswordActivity
		startActivity(new Intent(this, PasswordActivity_.class));
		overridePendingTransition(R.anim.open_last, R.anim.close_last);
		finish();
	}

	@UiThread
	public void showWrongPasswordToast() {
		ViewUtils.displayShortToast(getApplicationContext(), password_wrong);
	}

	/*@Click(R.id.chose_password_button)
	public void setPasswordButtonPressed() {
		// Check if there is a password
		if (password1.getText().toString().length() == 0) {
			ViewUtils.displayShortToast(getApplicationContext(), no_password_entered);
		} else
		// Check if passwords are equal
		if (!password1.getText().toString().equals(password2.getText().toString())) {
			ViewUtils.displayShortToast(getApplicationContext(), password_no_match);
		} else {
			// Check if password is weak and display a message to the user
			if (PasswordUtils.getStrength(this.password1.getText().toString()) < 5) {
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(password_weak_title);
				builder.setMessage(password_weak_description);
				builder.setPositiveButton(password_weak_ok, new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// If user decided to use the weak password, display the encrypting message
						progressDialog.setMessage(encrypting_data);
						progressDialog.show();
						// Use the entered password to encrypt the db
						setPasswordAndLogIn(password1.getText().toString()); // the method which should insert the valid passwords
					}
				});
				builder.setNegativeButton(password_weak_cancel, null);
				AlertDialog passwordWeakDialog = builder.create();
				passwordWeakDialog.show();
			} else {
				// If the password isn't weak, directly show the encrpying message
				progressDialog.setMessage(encrypting_data);
				progressDialog.show();
				// Use the enteres password to encrypt the db
				setPasswordAndLogIn(this.password1.getText().toString());
			}
		}
	}
*/
//	@UiThread
//	public void startSettingsActivity(String key) {
//		// Set the key for decrypting the db
//		ZerlinaApplication app = (ZerlinaApplication) this.getApplication();
//		app.setPassword(key);
//
//		// Start the SettingsActivity and disable the Back-Button in it
//		Intent intent = new Intent(this, SettingsActivity_.class);
//		intent.putExtra(SettingsActivity_.EXTRA_UP_BACK_ENABLED, false);
//		startActivity(intent);
//		overridePendingTransition(R.anim.open_next, R.anim.close_next);
//		finish();
//	}

	@UiThread
	public void startMainActivity(String key) {
		// Set the key for decrypting the db
		ZerlinaApplication app = (ZerlinaApplication) this.getApplication();
		app.setPassword(key);

		// Start the MainActivity
		startActivity(new Intent(this, XontaMainActivity_.class));
		overridePendingTransition(R.anim.open_next, R.anim.close_next);
		finish();
	}

	@Background
	public void setPasswordAndLogIn(String string) {
		// Make sure the data-db is really deleted
		

		// Open the password db
		PasswordSQLiteHelper helper = new PasswordSQLiteHelper(getApplicationContext());

		// Generate a random key
		String key = UUID.randomUUID().toString();
		String[] encrypted;
		try {
			// Encrypt the key and store it in the password db
			encrypted = PasswordUtils.encrypt(key, string );
			MPassword password = new MPassword(1, encrypted[0], 0, encrypted[1], encrypted[2]);
			helper.setPassword(password);

			
	    	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Finally dismiss the encrypting message
			dismissProgressDialog();
		}

	}

	@UiThread
	public void dismissProgressDialog() {
		progressDialog.dismiss();
	}

	@TextChange(R.id.chose_password_textfield_1)
	public void onTextChanges(TextView tv) {
		// When the user enters his password, display the password strength in the strenth progress bar
		//this.strengthBar.setProgress(PasswordUtils.getStrength(tv.getText().toString()));
	}

}