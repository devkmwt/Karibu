/*
 * File 		: ConnectionDetector.java
 * Author 		: Pankaj Goyal
 * Team 		: Doomshell Softwares Pvt Ltd (Android Development)
 * Description 	: This is a helper class. It is used to get the current
 * 				  network connection. whether its connected with Internet 
 * 				  or not. 
 */

package com.doomshell.karibu.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

    private Context _context;

    public ConnectionDetector(Context context){
        this._context = context;
    }

    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null)
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null)
                  for (int i = 0; i < info.length; i++)
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }

          }
          return false;
    }
}

