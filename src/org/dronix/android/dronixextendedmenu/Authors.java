package org.dronix.android.dronixextendedmenu;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Author: Ivan Morgillo
 * E-mail: imorgillo [at] gmail [dot] com
 * Date: 9/30/11
 */
public class Authors extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.authors);
    }
}