package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;


/**
 * User: harlem88
 * Date: 9/24/11
 * Time: 1:40 AM
 */
public class DataIcon {
    public DataIcon(Activity dti) {
          this.dti=dti;
        this.fsm = new FSmanager();

    }


          public static boolean isRunning(){
    String psOutput = DEMUtil.exec("grep \"ro.config.hw_opta=224\" /system/build.prop");
           if(psOutput.indexOf("ro.config.hw_opta=224")>0) {
            psOutput = DEMUtil.exec("grep \"ro.config.hw_optb=620\" /system/build.prop");
               if(psOutput.indexOf("ro.config.hw_optb=620")>0) {
                  return true;
               }
           } else return false;

          return false;

          }





  public void addIc() throws IOException, RootToolsException, InterruptedException {
    //  dti.showDialog(DIALOG_PROGRESS_IDAdd);
             pD =ProgressDialog.show(dti,"Enable Data Icon", "", true, false);
               /* fsm.mountRW();
                        fsm.setDataIcon();
                        fsm.mountRO();
                 */
                   TaskIconRemove tIR=new TaskIconRemove();
                   tIR.execute("Fix started ...", "1");
  }

  public void rmIc() throws IOException, RootToolsException, InterruptedException {

      pD =ProgressDialog.show(dti,"Disable Data Icon", "", true, false);
                   /* fsm.mountRW();
                            fsm.setDataIcon();
                            fsm.mountRO();
                     */
                       TaskIconRemove tIR=new TaskIconRemove();
                       tIR.execute("Fix started ...", "2");



      //      dti.showDialog(DIALOG_PROGRESS_IDRm);

  }


    private class TaskIconRemove extends AsyncTask<String, String, String> {


                @Override
                protected String doInBackground(String... params) {
                        publishProgress(params[0]);
                    try {
                        if(params[1].equalsIgnoreCase("1")){

                           fsm.mountRW();
                           fsm.setDataIcon();

                                   Thread.sleep(1000);

                            fsm.mountRO();

                            if(isRunning()) publishProgress("Fix completed!");

                        }
                            else

                        {

                             fsm.mountRW();
                        fsm.rmDataIcon();
                            Thread.sleep(1000);
                            if(!isRunning())publishProgress("Fix completed!");

                            fsm.mountRO();


                        }
                         } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (RootToolsException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    return "Finish !";
                }

                @Override
                protected void onProgressUpdate(String... values) {
                        pD.setMessage(values[0] + "\n");
                }

                @Override
                protected void onPostExecute(String result) {
                        pD.dismiss();
                }

        }

    private ProgressDialog pD;
   private final FSmanager fsm;
private Activity dti;
 private Context c;
}
