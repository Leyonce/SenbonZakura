package org.evenos.zerlina.activities;

import java.util.ArrayList;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.evenos.zerlina.model.ContactData;
import org.evenos.zerlina.model.ContactsDataList;
import org.evenos.zerlina.utils.ZerlinaApplication;

import android.app.ProgressDialog;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.itkamer.xonta.R;

/**
 * @author Eyog Yvon Léonce - grandeyl@gmail.com
 * 
 *         This class is where we read the contacts, store them, parse them,
 *         modify them and save them into the database.
 * 
 * */
/**
 * @author Eyog Yvon Léonce - grandeyl@gmail.com
 * 
 *         This class is where we read the contacts, store them, parse them,
 *         modify them and save them into the database.
 * 
 * */
@EActivity
public class XontaMainActivity extends ZerlinaActivity {

	@ViewById(R.id.updateContacts)
	Button updateContacts;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarbHandler = new Handler();
	private long progress_status = 0;
	int cntstat = 0;
	ImageView slidingimage;
	public int currentimageindex=0;
	int countOfContacts;
	private static final int shortSize = 8;
	private static final int longSize = 12;
	public int CLICKCOUNT = 0;
	private static char MTN_CHAR = '7';
	private static char OTHER_CHAR = '5';
	private static char ORANGE_CHAR = '9';
	private static char NEXTEL_CHAR = '6';
	private static char CAMTEL_CHAR = '2';
	private static char plus_char = '+';
	private static char zero_char = '0';
	private static char one_char = '1';
	private static char two_char = '2';
	private static char three_char = '3';
	private static char four_char = '4';
	private static char five_char = '5';
	private static char six_char = '6';
	private static char seven_char = '7';
	private static char eight_char = '8';
	private static char nine_char = '9';
	private static String MTN = "MTN";
	private static String ORANGE = "ORANGE";
	private static String CAMTEL = "CAMTEL";
	private static String NEXTEL = "NEXTTEL";
	private static String multiple = "mult";
	// Store the values of names and associated phone numbers.
	public ContactData contactsData = new ContactData();

	// List to store the contacts
	public static ContactsDataList contactsList = new ContactsDataList();
   
	public ArrayList<String> phonearray= new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xonta_main);
		 //disabling screen rotation, so that the update task is not interrupted
		 setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		 
		pressButton();
		ContentResolver cr = getBaseContext().getContentResolver();
        
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
       
        countOfContacts = cur.getCount();
        
        final Handler mHandler = new Handler();	
	}

	@UiThread
	//@Background
	public void disableUpdateContacts() {
		int clickcount = clickCount();
		if (clickcount == 1) {
			updateContacts.setEnabled(false);
			updateContacts.setOnKeyListener(null);
		}
	}
	@Background
	public int clickCount() {

		CLICKCOUNT++;
		return CLICKCOUNT;
	}

	
	//@Background
	public void parseNumber() {
		// Perform simple string manipulation as demanded in the specification
		// document

		try {
			for (ContactData c : contactsList.getContacts()) {

				String firstnum;

				firstnum = c.getPhone_number().toString().replaceAll("\\s", "");
				firstnum.replaceAll("\\s", "");

				// set local variables
				int numberSize1 = c.getPhone_number().length();
				if (numberSize1 == 8 || numberSize1 == 12) {

					char firstChar = firstnum.charAt(0);
					char secondChar = firstnum.charAt(1);
					char thirdChar = firstnum.charAt(2);
					char fourthChar = firstnum.charAt(3);
					char fifthChar = firstnum.charAt(4);
					char sixthChar = firstnum.charAt(5);

					checkFirstPN(c, firstChar, secondChar, thirdChar,
							fourthChar, fifthChar, sixthChar, numberSize1,
							firstnum);

				} // end of if loop
			}// end of for loop

		} // end of try block

		catch (Exception e) {

			e.printStackTrace();
		}
	}

	// @Background
	public void checkFirstPN(ContactData c, char firstChar, char secondChar,
			char thirdChar, char fourthChar, char fifthChar, char sixthChar,
			int numberSize, String firstnum) {
		// check if number size is equal to 8 and first char is:

		switch (numberSize) {
		case shortSize:
			if (firstChar == MTN_CHAR) {
				c.setPhone_number('6' + c.getPhone_number());
				c.setOperator_name(MTN);
				break;
			}// end of short check MTN
			else if (firstChar == ORANGE_CHAR) {
				c.setPhone_number('6' + c.getPhone_number());
				c.setOperator_name(ORANGE);
				break;
			}// end of short check ORANGE
			else if (firstChar == CAMTEL_CHAR) {
				c.setPhone_number('6' + c.getPhone_number());
				c.setOperator_name(CAMTEL);
				break;
			}// end of short check CAMTEL
			else if (firstChar == NEXTEL_CHAR) {
				c.setPhone_number('6' + c.getPhone_number());
				c.setOperator_name(NEXTEL);
				break;
			}// end of short check NEXTEL
			else {
				if (firstChar == OTHER_CHAR) {
					if (secondChar == zero_char || secondChar == one_char
							|| secondChar == two_char
							|| secondChar == three_char
							|| secondChar == four_char) {
						c.setPhone_number('6' + c.getPhone_number());
						c.setOperator_name(MTN);
						break;
					}// end of MTN short check
					else if (secondChar == five_char || secondChar == six_char
							|| secondChar == seven_char
							|| secondChar == eight_char
							|| secondChar == nine_char) {
						c.setPhone_number('6' + c.getPhone_number());
						c.setOperator_name(ORANGE);
						break;
					}// end of ORANGE short check
				}
			}
			break;

		case longSize:
			// check for '+237' in longer numbers and insert '6' at fourth
			// position or a '2' for CAMTEL numbers
			if (firstChar == plus_char && secondChar == two_char
					&& thirdChar == three_char && fourthChar == seven_char) {
				if (fifthChar == MTN_CHAR) {
					firstnum = firstnum.substring(0, 4) + "6"
							+ firstnum.substring(4, firstnum.length());
					c.setPhone_number(firstnum);
					c.setOperator_name(MTN);
					break;
				}// end of MTN long check

				else if (fifthChar == ORANGE_CHAR) {
					firstnum = firstnum.substring(0, 4) + "6"
							+ firstnum.substring(4, firstnum.length());
					c.setPhone_number(firstnum);
					c.setOperator_name(ORANGE);
					break;
				}// end of ORANGE long check

				else if (fifthChar == NEXTEL_CHAR) {
					firstnum = firstnum.substring(0, 4) + "6"
							+ firstnum.substring(4, firstnum.length());
					c.setPhone_number(firstnum);
					c.setOperator_name(NEXTEL);
					break;
				}// end of NEXTEL long check
				else if (fifthChar == CAMTEL_CHAR) {
					firstnum = firstnum.substring(0, 4) + "2"
							+ firstnum.substring(4, firstnum.length());
					c.setPhone_number(firstnum);
					c.setOperator_name(CAMTEL);
					break;
				}// end of CAMTEL long check
				else if (fifthChar == OTHER_CHAR) {
					if (sixthChar == zero_char || sixthChar == one_char
							|| sixthChar == two_char || sixthChar == three_char
							|| sixthChar == four_char) {
						firstnum = firstnum.substring(0, 4) + "6"
								+ firstnum.substring(4, firstnum.length());
						c.setPhone_number(firstnum);
						c.setOperator_name(MTN);
						break;
					}// end of other MTN long check
					else if (sixthChar == five_char || sixthChar == six_char
							|| sixthChar == seven_char
							|| sixthChar == eight_char
							|| sixthChar == nine_char) {
						firstnum = firstnum.substring(0, 4) + "6"
								+ firstnum.substring(4, firstnum.length());
						c.setPhone_number(firstnum);
						c.setOperator_name(ORANGE);
						break;
					}// end of other ORANGE long check
				}
			}// end of long checks

			break;
		default:
			break;

		}

	}

	public void pressButton() {

		// updateContacts = (Button) findViewById(R.id.updateContacts);
		updateContacts.setOnClickListener(new OnClickListener() {
			// function called when button is clicked
        	@Override
            public void onClick(View view) {
 			
                // creating a new progress dialog, and displaying it
                progressBar = new ProgressDialog(view.getContext());
                
                /*makig the progress dialog uncancellable by the user
                this is to avoid situations where all contacts are not updated
                */
                progressBar.setCancelable(false);
                
                progressBar.setMessage("Xonta is Updating Your Contacts ...\n Hold on!");
                
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                
                progressBar.setProgress(0);
                
                //setting the maximum value of the progress bar
				progressBar.setMax(countOfContacts);
				
				progressBar.show();
 
                progressBarStatus = 0;

                progress_status = 0;
 
                new Thread(new Runnable() {
 
                    public void run() {
                        while (progressBarStatus < countOfContacts) {
 
                            // simulate progress bar operation while the status is 
                        	//less than the maximum value set , that is, the total number of contacts
                            progressBarStatus = xonting();
 
                            // sleep 1/2 second 
                            try {
                                Thread.sleep(250);
                                
                            } catch (InterruptedException e) {
                            	
                                e.printStackTrace();
                            }
 
                            // then Update the progress bar
                            progressBarbHandler.post(new Runnable() {
                            	
                                public void run() {
                                	
                                    progressBar.setProgress(progressBarStatus);
                                }
                            });
                        }
 
                        /*when all contacts have been updated,
                        * that is, when the progress bar indicates 100 percent,
                        */
                        if (progressBarStatus >= countOfContacts) {
 
                            // sleep 1 second, so that the user can see the 100%
                            try {
                                Thread.sleep(500);
                                
                            } catch (InterruptedException e) {
                            	
                                e.printStackTrace();
                            }
                            //enabling back the screen rotation
                            //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                            
                            //enabling back the screen rotation
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                            
                            // and then close the progressbar dialog
                            progressBar.dismiss();
                            
                            //so we now start the About us activity with the use of an intent
                         startAboutUSActivity();
                        }
                    }

					
                }).start();
                
                readContactsData();
    			// Display the number of contacts
    			Toast.makeText(getApplicationContext(),
    					"read " + " contacts. ", Toast.LENGTH_SHORT).show();
    			
    			parseNumber();
    			updateContactsData();
    			clickCount();
    			disableUpdateContacts();
    			}
		});
	}

	// simulating the operation of the progress bar
		 //This is where we can include the operation to calculate the percentage corresponding to the number of contacts in the phone
		 //Since you are first reading all the contacts and then updating all, it is good if we
		 //give an arbitrary time for the update of each contact , and then work from there
		    public int xonting() {
		 
		        while (progress_status <= countOfContacts) {
		 
		        	for(cntstat = 0; cntstat <= countOfContacts;){
		        		
		        		progress_status++;
		        		cntstat++;
		        		return (int) progress_status;
		    
		        	}
		        }
		        return cntstat;
		    }
	@Background
	public void readContactsData() {

		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);

		while (phones.moveToNext()) {
			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			// Get Contact Id
			String contact_id = ""
					+ phones.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
			int phonetype = phones
					.getInt(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
			// Store the values of names and associated
			// phone numbers.
			ContactData contactsData = new ContactData();
			// Set contact name and phone number
			// i.e parse the SQLlite query to set the POJO's
            phonearray.add(phoneNumber);
			contactsData.setPhone_number(phoneNumber.replaceAll("\\s", ""));
			contactsData.setContact_name(name);
			contactsData.setPhonetype(phonetype);
			contactsData.setContact_ID(contact_id);
			// Add Contacts to list
			contactsList.addContact(contactsData);

		}
		phones.close();
	}

	// }

	private void startAboutUSActivity() {
		// Set the key for decrypting the db
    		ZerlinaApplication app = (ZerlinaApplication) this.getApplication();
    		// Start the MainActivity
    		startActivity(new Intent(this, AboutUS_.class));
    		overridePendingTransition(R.anim.open_next, R.anim.close_next);
    		finish();
	}
	// @UiThread
	@Background
	public void updateContactsData() {

		for (ContactData c : contactsList.getContacts()) {
			int id = Integer.parseInt(c.getContact_ID());
			int numbertype = c.getPhonetype();
			String name = c.getOperator_name();
			String firstnum = c.getPhone_number();
			// String secondnum=c.getSecondPN();
			// String thirdnum=c.getThirdPN();
			// String fouthnum=c.getFouthPN();
			ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

			// Name
			android.content.ContentProviderOperation.Builder builder = ContentProviderOperation
					.newUpdate(ContactsContract.Data.CONTENT_URI);
			builder.withSelection(
					ContactsContract.Data.CONTACT_ID + "=?" + " AND "
							+ ContactsContract.Data.MIMETYPE + "=?",
					new String[] {
							String.valueOf(id),
							ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE });
			builder.withValue(
					ContactsContract.CommonDataKinds.StructuredName.SUFFIX,
					name);
			ops.add(builder.build());

			builder = ContentProviderOperation
					.newUpdate(ContactsContract.Data.CONTENT_URI)
					.withSelection(
							ContactsContract.Data.CONTACT_ID
									+ "=?"
									+ " AND "
									+ ContactsContract.Data.MIMETYPE
									+ "=?"
									+ " AND "
									+ ContactsContract.CommonDataKinds.Phone.TYPE
									+ "=?",
							new String[] {
									String.valueOf(id),
									ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
									String.valueOf(numbertype) });
			builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
					firstnum).withValue(
					ContactsContract.CommonDataKinds.Phone.TYPE, numbertype);
			ops.add(builder.build());
			// Update
			try {
				getContentResolver()
						.applyBatch(ContactsContract.AUTHORITY, ops);
			} catch (Exception e) {
				e.printStackTrace();
			}

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
		if (id == R.id.updateContacts) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// If Back-Button was pressed in MainActivity, log out and display
		// PasswordActivity
		ZerlinaApplication app = (ZerlinaApplication) getApplication();
		app.setPassword(null);
		phonearray.clear();
        contactsList.getContacts().clear();
		Intent intent = new Intent(this, PasswordActivity_.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		overridePendingTransition(R.anim.open_last, R.anim.close_last);
		finish();
	}
}