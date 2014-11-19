package org.evenos.zerlina.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Helper class for different View related stuff like displaying toasts
 * 
 * @author Jan Thielemann
 * 
 */
public class ViewUtils {
	public static void setTextOrDeactivate(String string, EditText textField, TextView label, ImageView... imageViews) {
		if (string != null && string.length() > 0) {
			textField.setText(string);
		} else {
			// If there is no text, deactivate the label, edittext and images
			if (textField != null)
				textField.setVisibility(EditText.GONE);
			if (label != null)
				label.setVisibility(TextView.GONE);
			if (imageViews != null)
				for (ImageView iv : imageViews)
					iv.setVisibility(ImageView.GONE);
		}
	}

	public static void displayShortToast(Context context, String msg) {
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public static void displayLongToast(Context context, String msg) {
		Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
