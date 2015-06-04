package com.asus.applinkreceiver;

import android.content.Intent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class DeepLinkReceiver extends CordovaPlugin {
    private static CordovaWebView gWebView;
    public static String tmpParam = "";

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("Init")){
            Intent intent = new Intent(cordova.getActivity(),DeepLinkService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cordova.getActivity().startService(intent);

            gWebView = this.webView;

            if (tmpParam != "") {
                sendJavascript(tmpParam);
                tmpParam = "";
            }
        }
		
        return true;
    }

    /*
	 * Sends a json object to the client as parameter to a method which is defined in gECB.
	 */
    public static void sendJavascript(String str) {
        String _d = "javascript:" + "launchObj.open('" + str + "')";

        if (gWebView != null) {
            gWebView.sendJavascript(_d);
        }
    }

    public static boolean isActive()
    {
        return gWebView != null;
    }
}
