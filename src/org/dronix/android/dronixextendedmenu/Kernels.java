package org.dronix.android.dronixextendedmenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;

/**
 * Author: Ivan Morgillo
 * E-mail: imorgillo [at] gmail [dot] com
 * Date: 9/30/11
 */
public class Kernels extends PreferenceActivity {
    private DEMUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        util = new DEMUtil(this);
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.kernels);

        Preference kernel_standard = (Preference) findPreference("kernel_standard");
        kernel_standard.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                loadStandardKernel();
                return false;
            }
        });

        Preference kernel_usbhost = (Preference) findPreference("kernel_usbhost");
        kernel_usbhost.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                loadUSBhostKernel();
                return false;
            }
        });


    }

    private boolean loadStandardKernel() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(getString(R.string.are_you_sure));

        // set a positive/yes button and create a listener
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                try {
                    RootTools.sendShell("/system/xbin/flash_image boot /cust/boot-standard.img");
                    util.tl(getString(R.string.kernel_standard_flashed));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RootToolsException e) {
                    e.printStackTrace();
                }
            }
        });

        // set a negative/no button and create a listener
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                util.ts(getString(R.string.wise_decision));
            }
        });
        alertbox.show();
        return false;
    }

    private boolean loadUSBhostKernel() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage(getString(R.string.are_you_sure));

        // set a positive/yes button and create a listener
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                try {
                    RootTools.sendShell("/system/xbin/flash_image boot /cust/boot-usbhost.img");
                    util.tl(getString(R.string.kernel_usbhost_flashed));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RootToolsException e) {
                    e.printStackTrace();
                }
            }
        });

        // set a negative/no button and create a listener
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                util.ts(getString(R.string.wise_decision));
            }
        });
        alertbox.show();
        return false;
    }
}