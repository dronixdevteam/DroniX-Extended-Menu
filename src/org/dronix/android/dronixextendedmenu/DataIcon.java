package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;


/**
 * User: harlem88
 * Date: 9/24/11
 * Time: 1:40 AM
 */
public class DataIcon extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fsm = new FSmanager();
        Intent i = getIntent();
        String operation = i.getStringExtra("operation");
        if (operation.equals("enable"))
            try {
                addIc();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RootToolsException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        else if (operation.equals("disable"))
            try {
                rmIc();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RootToolsException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    public static boolean isRunning() {
        String psOutput = DEMUtil.exec("grep \"ro.config.hw_opta=224\" /system/build.prop");
        if (psOutput.indexOf("ro.config.hw_opta=224") > 0) {
            psOutput = DEMUtil.exec("grep \"ro.config.hw_optb=620\" /system/build.prop");
            if (psOutput.indexOf("ro.config.hw_optb=620") > 0) {
                return true;
            }
        } else return false;
        return false;
    }


    public void addIc() throws IOException, RootToolsException, InterruptedException {
        //  dti.showDialog(DIALOG_PROGRESS_IDAdd);
        showDialog(PDDIALOG);
        /* fsm.mountRW();
               fsm.setDataIcon();
               fsm.mountRO();
        */
        TaskIconRemove tIR = new TaskIconRemove();
        tIR.execute("Fix started ...", "1");
    }

    public void rmIc() throws IOException, RootToolsException, InterruptedException {

        showDialog(PDDIALOG);
        pD.setMessage("Disable Data Icon");
        /* fsm.mountRW();
               fsm.setDataIcon();
               fsm.mountRO();
        */
        TaskIconRemove tIR = new TaskIconRemove();
        tIR.execute("Fix started ...", "2");
    }
    private class TaskIconRemove extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            publishProgress(params[0]);
            try {
                if (params[1].equalsIgnoreCase("1")) {

                    fsm.mountRW();
                    fsm.setDataIcon();
                    Thread.sleep(1000);
                    fsm.mountRO();
                    if (isRunning()) publishProgress("Fix completed!");
                  }
                else
                    {
                    fsm.mountRW();
                    fsm.rmDataIcon();
                    Thread.sleep(1000);
                    if (!isRunning()) publishProgress("Fix completed!");
                    fsm.mountRO();
                     }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RootToolsException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish !";
        }
        @Override
        protected void onProgressUpdate(String... values) {
            pD.setMessage(values[0] + "\n");
        }

        @Override
        protected void onPostExecute(String result) {
            showDialog(CDDIALOG);
        }
       }
      protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PDDIALOG: {
                pD = new ProgressDialog(this);
                pD.setTitle("DataIcon");
                pD.setIndeterminate(false);
                pD.setCancelable(false);
                pD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                return pD;
            }
            case CDDIALOG: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert");
                builder.setMessage("Click reboot now to apply the change");
                builder.setCancelable(false);
                builder.setPositiveButton("reboot", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        pD.dismiss();
                        try {
                            dialog.dismiss();
                            fsm.reboot();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (RootToolsException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                 });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        pD.dismiss();
                        dialog.dismiss();
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                return alertDialog;
            }
        }
        return null;
    }
    private FSmanager fsm;

    private Context c;
    private static final int PDDIALOG = 0;
    private static final int CDDIALOG = 1;
    private static ProgressDialog pD;
    private static AlertDialog alertDialog;
}