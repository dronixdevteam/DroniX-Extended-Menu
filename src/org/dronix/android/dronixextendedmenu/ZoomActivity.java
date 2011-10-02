package org.dronix.android.dronixextendedmenu;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

/**
  * User: harlem88
 * Date: 9/28/11
 * Time: 3:26 PM
  */
public class ZoomActivity extends Activity {

    @Override
   public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
            Intent intent=getIntent();
	        // int drw=Integer.parseInt(intent.getStringExtra("drawable"));
                            int drw= intent.getIntExtra("drw", 0) ;
            TextView view = null;
            //   view.append(intent.getStringExtra("drw"));
            Bitmap btm = BitmapFactory.decodeResource(getResources(), drw);
            ImageZoom img=new ImageZoom(this);
            //Toast.makeText(this, , 2000).show();
            img.setImageBitmap(btm);
            setContentView(img);





    }

@Override
public  void onPause(){

    super.onPause();

}



}
