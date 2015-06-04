package com.asus.applinkreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Webber_Lin on 2015/6/3.
 */
public class HybridAppBroadcastRcver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if( action.compareTo("android.intent.action.BOOT_COMPLETED") == 0 ) {
            Intent service = new Intent(context, DeepLinkService.class);
            context.startService(service);
        }
    }
}
