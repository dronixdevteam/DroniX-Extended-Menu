package org.dronix.android.dronixextendedmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import com.stericson.RootTools.RootToolsException;

import java.io.*;






/**
 * Created by IntelliJ IDEA.
 * User: harlem88
 * Date: 9/24/11
 * Time: 1:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataIcon {
    public DataIcon(Activity dti) {
          this.dti=dti;
        this.fsm = new FSmanager();

    }


          public static boolean isRunning() throws IOException {

              File rootStore = Environment.getRootDirectory();

                      String path=rootStore+"/build.prop";
                      File file=new File(path);
                     FileInputStream instream = null;
                          try {
                              instream = new FileInputStream(file);
                          } catch (FileNotFoundException e1) {
                              // TODO Auto-generated catch block
                              e1.printStackTrace();
                          }
                          boolean checked=false;
                        InputStreamReader inputreader = new InputStreamReader(instream);
                        BufferedReader buffreader = new BufferedReader(inputreader);

                        String line;

                          while((line = buffreader.readLine()) != null){
                              if((line.equalsIgnoreCase("ro.config.hw_opta=224"))){
                                  line = buffreader.readLine();
                                  if((line.equalsIgnoreCase("ro.config.hw_optb=620"))){
                                          checked=true;

                                  }
                              }
                               else
                                  checked=false;
                            }
        return checked;
          }


  public void addIc() throws IOException, RootToolsException, InterruptedException {
    //  dti.showDialog(DIALOG_PROGRESS_IDAdd);
                fsm.mountRW();
                        fsm.setDataIcon();
                        fsm.mountRO();

  }

  public void rmIc() throws IOException, RootToolsException, InterruptedException {
                      fsm.mountRW();
                        fsm.rmDataIcon();
                        fsm.mountRO();
      //      dti.showDialog(DIALOG_PROGRESS_IDRm);

  }







	private static final int DIALOG_PROGRESS_IDAdd = 1;
	private static final int DIALOG_PROGRESS_IDRm = 2;
	private static final int DIALOG_CONFIRM_ID= 3;
	private static final int DIALOG_OK_ID=4;


   private final FSmanager fsm;
private Activity dti;
 private Context c;
}
