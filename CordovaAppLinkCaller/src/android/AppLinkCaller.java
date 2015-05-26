package com.asus.applinkcaller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import bolts.AppLink;
import bolts.AppLinkNavigation;
import bolts.Continuation;
import bolts.Task;
import bolts.WebViewAppLinkResolver;


public class AppLinkCaller extends CordovaPlugin {
    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final String URL = args.getString(0);
        final AppLinkCaller caller = AppLinkCaller.this;

        if (action.equals("OpenLink")){
            new WebViewAppLinkResolver(cordova.getActivity().getApplicationContext()).getAppLinkFromUrlInBackground(Uri.parse(URL)).continueWith(
                    new Continuation<AppLink, AppLinkNavigation.NavigationResult>() {
                        @Override
                        public AppLinkNavigation.NavigationResult then(Task<AppLink> task) {
                            AppLink link = task.getResult();

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            AppLink.Target target = link.getTargets().get(0);
                            intent.setPackage(target.getPackageName());
                            intent.setData(link.getSourceUrl());

                            ResolveInfo resolveInfo = cordova.getActivity().getApplicationContext().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                            if (resolveInfo != null) {
                                Bundle extras = new Bundle();
                                extras.putString("target_url", URL);

                                intent.putExtra("al_applink_data", extras);
                                cordova.startActivityForResult((CordovaPlugin) caller,intent,0);
                            }

                            return null;
                        }
                    }
            );
        }

        return true;
    }
}
