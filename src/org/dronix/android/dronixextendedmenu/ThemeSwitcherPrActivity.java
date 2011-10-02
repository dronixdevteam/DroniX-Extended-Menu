package org.dronix.android.dronixextendedmenu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * User: harlem88
 * Date: 9/29/11
 * Time: 12:09 AM
 */
public class ThemeSwitcherPrActivity extends PreferenceActivity{


    @Override
        protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.theme_prefs);


    }

    @Override
    protected void onPause(){
    super.onPause();

}
}
