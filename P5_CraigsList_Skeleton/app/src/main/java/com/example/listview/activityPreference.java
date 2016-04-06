/**
 * 
 */
package com.example.listview;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class activityPreference extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PrefsFragment prefFragment = new PrefsFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, prefFragment);
		fragmentTransaction.commit();
		//TODO your stuff here
	}
	
	public static class PrefsFragment extends PreferenceFragment {
	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
			//TODO your stuff here
		}
	}
}

