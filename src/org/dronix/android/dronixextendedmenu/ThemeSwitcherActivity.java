package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;


/**
 * User: harlem88
 * Date: 9/29/11
 * Time: 12:42 AM
 */
public class ThemeSwitcherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fsm = new FSmanager();
        setContentView(R.layout.themeswt);

        this.checkT = 0;
        checkdf = (CheckBox) findViewById(R.id.checkdefault);
        checkdr = (CheckBox) findViewById(R.id.checkdronix);
        checkggr = (CheckBox) findViewById(R.id.checkginger);
        checksns = (CheckBox) findViewById(R.id.checksense);
        checkcst = (CheckBox) findViewById(R.id.checkcustom);

        theme = checkTheme();
        setChecks(theme);

        checkdf.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setCheck(0);
                /*pD = ProgressDialog.show(ThemeSwitcherActivity.this,
                        getString(R.string.theme_switcher),
                        getString(R.string.theme_switcher_set_default_reboot),
                        true, false);

                tAt = new TaskApplyTheme();
                tAt.execute("0", "restore");
                */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyTheme doTask = new TaskApplyTheme();
                    doTask.execute(getString(R.string.theme_switcher_set_default_reboot), "restore", "");
            }
        });
        checkdr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   setCheck(1);
                pD = ProgressDialog.show(ThemeSwitcherActivity.this,
                        getString(R.string.theme_switcher),
                        getString(R.string.theme_switcher_set_04_reboot),
                        true, false);

                tAt = new TaskApplyTheme();
                tAt.execute("1", "dronix04");
                */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyTheme doTask = new TaskApplyTheme();
                    doTask.execute(getString(R.string.theme_switcher_set_04_reboot), "dronix04", "");

            }
        });
        checkggr.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setCheck(2);
                pD = ProgressDialog.show(ThemeSwitcherActivity.this,
                        getString(R.string.theme_switcher),
                        getString(R.string.theme_switcher_set_ginger_reboot),
                        true, false);

                tAt = new TaskApplyTheme();
                tAt.execute("2", "ginger");
                  */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyTheme doTask = new TaskApplyTheme();
                    doTask.execute(getString(R.string.theme_switcher_set_ginger_reboot), "ginger", "");
            }

        });
        checksns.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setCheck(3);
                pD = ProgressDialog.show(ThemeSwitcherActivity.this,
                        getString(R.string.theme_switcher),
                        getString(R.string.theme_switcher_set_sense_reboot),
                        true, false);

                tAt = new TaskApplyTheme();
                tAt.execute("3", "sense");
                */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyTheme doTask = new TaskApplyTheme();
                    doTask.execute(getString(R.string.theme_switcher_set_sense_reboot), "sense", "");
            }
        });
        checkcst.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /*setCheck(4);
                pD = ProgressDialog.show(ThemeSwitcherActivity.this,
                        getString(R.string.theme_switcher),
                        getString(R.string.theme_switcher_set_custom_reboot),
                        true, false);

                tAt = new TaskApplyTheme();
                tAt.execute("4", "custom");
                */
                showDialog(PDDIALOG);
                progressBar= 0;
                pD.setProgress(0);
                TaskApplyTheme doTask = new TaskApplyTheme();
                    doTask.execute(getString(R.string.theme_switcher_set_custom_reboot), "custom", "");
            }
        });

        ImageView default1 = (ImageView) findViewById(R.id.default1);
        default1.setOnClickListener(new OnClickListener() {
            //   setContentView(new Zoom(.this));
            @Override
            public void onClick(View view) {
                initActivity(R.drawable.default1);
            }
        });

        ImageView dronix04 = (ImageView) findViewById(R.id.dronix04);
        dronix04.setOnClickListener(new OnClickListener() {
            //   setContentView(new Zoom(.this));
            @Override
            public void onClick(View view) {
                initActivity(R.drawable.dronix04);
            }
        });

        ImageView ginger = (ImageView) findViewById(R.id.ginger);
        ginger.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initActivity(R.drawable.ginger);
            }
        });

        ImageView sense = (ImageView) findViewById(R.id.sense);
        sense.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                initActivity(R.drawable.sense);
            }
        });
    }

    protected void initActivity(int drw) {
        Intent intent = new Intent(ThemeSwitcherActivity.this, ZoomActivity.class);
        intent.putExtra("drw", drw);
        startActivity(intent);
    }

    private class TaskApplyTheme extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
         Thread thr=new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (progressBar<100) {
                                try {
                                   Thread.sleep(197);
                                       } catch (InterruptedException e) {
                                               e.printStackTrace();
                                       }
                                    progressBar++;
                                    pD.setProgress(progressBar);

                                   }
                                }
                               });
                        thr.start();
                        try {
                            fsm.setTheme(params[1]);
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
    }

    public void setCheck(int checkT) {
        if (checkT == 0)
            checkdf.setChecked(true);
        else checkdf.setChecked(false);
        if (checkT == 1)
            checkdr.setChecked(true);
        else checkdr.setChecked(false);
        if (checkT == 2)
            checkggr.setChecked(true);
        else checkggr.setChecked(false);
        if (checkT == 3)
            checksns.setChecked(true);
        else checksns.setChecked(false);
        if (checkT == 4)
            checkcst.setChecked(true);
        else checkcst.setChecked(false);
    }

    private void setChecks(String theme) {
        if (theme.contains("default"))
            checkdf.setChecked(true);
        else
            checkdf.setChecked(false);

        if (theme.contains("dronix04"))
            checkdr.setChecked(true);
        else
            checkdr.setChecked(false);

        if (theme.contains("ginger"))
            checkggr.setChecked(true);
        else
            checkggr.setChecked(false);

        if (theme.contains("sense"))
            checksns.setChecked(true);
        else
            checksns.setChecked(false);

        if (theme.contains("custom"))
            checkcst.setChecked(true);
        else
            checkcst.setChecked(false);
    }

     protected Dialog onCreateDialog(int id) {
         switch (id) {
             case PDDIALOG: {
                 pD = new ProgressDialog(this);
                 pD.setTitle(getString(R.string.theme_switcher));
                 pD.setMessage(getString(R.string.theme_switcher_set_generic_reboot));
                 pD.setIndeterminate(false);
                 pD.setCancelable(false);
                 pD.setMax(100);
                 pD.setProgress(0);
                 pD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                 return pD;
             }
         }
        return null;
     }

    public String checkTheme() {
        String theme = "";
        theme = DEMUtil.exec("grep THEME /etc/dronix.prop");
        theme = theme.substring(6);
        return theme;
    }

    private Drawable image;
    private CheckBox checkdf, checkdr, checkggr, checksns, checkcst;
    private int checkT;
    private FSmanager fsm;
    private String theme;
    private static ProgressDialog pD;
    private TaskApplyTheme tAt;
    private static int progressBar;
    private static final int PDDIALOG = 0;
}