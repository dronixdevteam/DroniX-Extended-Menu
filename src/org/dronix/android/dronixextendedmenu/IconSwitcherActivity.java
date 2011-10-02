package org.dronix.android.dronixextendedmenu;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;

/**
 * User: harlem88
 * Date: 9/29/11
 * Time: 8:37 PM
 */
public class IconSwitcherActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.iconswt);
        fsm=new FSmanager();

                this.checkT=0;
                 checkbt1=(CheckBox) findViewById(R.id.check_i_battery1);
                 checkbt2=(CheckBox) findViewById(R.id.check_i_battery2);

               final DEMUtil dem=new DEMUtil(this);




        checkbt1.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

              pD=ProgressDialog.show(IconSwitcherActivity.this,"Icon Switcher", "Set  battery 1 icon , now reboot framework",  true ,false);

                                         TaskApplyIcon tAt=new TaskApplyIcon();
                                         tAt.execute("0", "battery1");

            }

        }   );



        checkbt2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
               pD=ProgressDialog.show(IconSwitcherActivity.this,"Icon Switcher", "Set  battery 2 icon , now reboot framework",  true ,false);

                       TaskApplyIcon tAt=new TaskApplyIcon();
                        tAt.execute("1", "battery2");



            }
        });





    }
    private void setCheck(int checkT){

                    if(checkT==0)
                        checkbt1.setChecked(true);
                     else checkbt1.setChecked(false);
                    if(checkT==1)
                                   checkbt2.setChecked(true);
                                else checkbt2.setChecked(false);



    };
    private class TaskApplyIcon extends AsyncTask<String, String, String> {


                   @Override
                   protected String doInBackground(String... params) {
                           try{
                             int set=Integer.parseInt(params[0]);
                             setCheck(set);

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
   private void setChecks(String theme) {


        if (theme.contains("battery1"))

            checkbt1.setChecked(true);

        else checkbt1.setChecked(false);

        if (theme.contains("battery2"))
            checkbt2.setChecked(true);

        else checkbt2.setChecked(false);



    }
    public String checkTheme(){
       String theme="";
      theme=DEMUtil.exec("grep BATTERY /etc/dronix.prop");
            theme=theme.substring(7);
        return theme;
    }



    private ProgressDialog pD;
    private int checkT;
    private FSmanager fsm;
private CheckBox checkbt1, checkbt2;
}
