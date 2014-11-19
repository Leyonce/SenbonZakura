package org.evenos.zerlina.activities;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itkamer.xonta.R;
/**
 * Class to display information concerning the developpers of the application, that is, ITkamer and Landmark university
 * 
 * @author SONDI Mikael Raphael at mikaelsondi@gmail.com
 * 
 * 
 */
@EActivity(R.layout.activity_about_us)
public class AboutUS extends ZerlinaActivity {
    @ViewById(R.id.informFriends)
	Button startBtn;




		String smsbody="Hello, I have an android phone and I just updated all my phone numbers in a single click, prefixing them with \'6\' or \'2\', depending on the network operator, with the use of Xonta, an application developped by ITKamer.It is located opposite \'societe generale bank\' at ub junction, Buea. For more information, text or call the number: 678057792 or 671644542";
		String funtext= "Thank you for sending this sms.Xonta owes you lunch.";
		protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	        
	        //setContentView(R.layout.activity_about_us);
	        //Toast to indicate that the contacts have been updated
	        Toast.makeText(this, "Your contacts have been updated !",
					Toast.LENGTH_LONG).show();
	        //Button to be clicked to call the sms intent
	        //Button 
	        //startBtn = (Button) findViewById(R.id.informfriends);
	        // listener for the button's on click event
	        startBtn.setOnClickListener(new View.OnClickListener() {
	           public void onClick(View view) {
	           sendSMS();
	        }
	     });
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
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    /*
	     * Function that is called to send SMS, informing others about the application
	     * The body of the sms is stored in the string declared above, 'smsbody'
	        */
	    @Background
	    protected void sendSMS() {
	        Log.i("Send SMS", "");

	        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
	        smsIntent.setData(Uri.parse("smsto:"));
	        smsIntent.setType("vnd.android-dir/mms-sms");
	        
	        //Setting the phone number of recipient
	        //smsIntent.putExtra("address"  , new String ("678057792"));
	        
	        smsIntent.putExtra("sms_body", "Hello, I have an android phone and I just updated all my phone numbers in a single click, prefixing them with \'6\' or \'2\', depending on the network operator, with the use of Xonta, an application developped by ITKamer.Text or call: 678057792 or 671644542");
	        try {
	           startActivity(smsIntent);
	           finish();
	           Log.i("Finished sending SMS...", "");
	           Toast.makeText(this, funtext, Toast.LENGTH_LONG).show();
	        } catch (android.content.ActivityNotFoundException ex) {
	           Toast.makeText(this, 
	           "SMS failed, please try again later.", Toast.LENGTH_LONG).show();
	        }
	     }
	    
	}

