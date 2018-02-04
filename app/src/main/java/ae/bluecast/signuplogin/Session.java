package ae.bluecast.signuplogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASUS on 9/18/2017.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
     public  Session(Context ctx){
         this.ctx = ctx;
         prefs = ctx.getSharedPreferences("myapp",Context.MODE_PRIVATE);
         editor = prefs.edit();

     }
     public  void setLoggedIn(boolean loggedIn){
         editor.putBoolean("loggedInMode",loggedIn);
         editor.commit();
     }
     public boolean loggedIn(){
         return prefs.getBoolean("loggedinmode",false);
     }
}
