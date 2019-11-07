package com.rscbyte.homechurch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.orm.SugarContext;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.activities.Dashboard;
import com.rscbyte.homechurch.models.MProfile;

public class SplashScreen extends AppCompatActivity {
    boolean firstCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //initialize suger orm
        SugarContext.init(this);
        //set toolbar for mobile
        Tools.setSystemBarColor(this, R.color.app_color_1);
        //check for permission
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermission(Manifest.permission.READ_SMS);
            }
        }, 3000);
        //load license copy text
        if (MProfile.count(MProfile.class) > 0) {
            String name = (MProfile.findById(MProfile.class, 1)).getNames();
            ((TextView) findViewById(R.id.txt_license)).setText(String.valueOf("licensed to " + name.toLowerCase()));
        }
        //Set Tool bar
        Tools.setSystemBarLight(this);
    }

    //launcher
    public void startMain() {
        //Think to start the new class
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
                finish();
            }
        }, 3000);
    }

    //Simple permission to check
    public void checkPermission(String permission) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            //Open permission screen
            firstCheck = true;
            startActivity(new Intent(SplashScreen.this, Permissions.class).putExtra("permission", Manifest.permission.READ_SMS));
        } else {
            startMain();
        }
    }

    //onResume activities
    @Override
    protected void onResume() {
        super.onResume();
        if (firstCheck)
            checkPermission(Manifest.permission.READ_SMS);
    }
}