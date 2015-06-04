package com.asus.applinkreceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by Webber_Lin on 2015/5/27.
 */
public class DeepLinkRcvActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(R.layout.activity_app_link_receiver);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        if (getIntent().getExtras() != null)
            tv1.setText(getIntent().getExtras().getString("txt"));
        else {
            tv1.setText("");
            *//*Intent intent = new Intent(DeepLinkRcvActivity.this,DeepLinkService.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startService(intent);*//*
        }*/

        DeepLinkReceiver.tmpParam = getIntent().getExtras().getString("param");

        finish();

        if (! DeepLinkReceiver.isActive()) {
            forceMainActivityReload();
        }
    }

    /**
     * Forces the main activity to re-launch if it's unloaded.
     */
    private void forceMainActivityReload()
    {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(getApplicationContext().getPackageName());
        startActivity(launchIntent);
    }
}
