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
import com.rscbyte.homechurch.activities.SignIn;
import com.rscbyte.homechurch.models.MProfile;

public class SplashScreen extends AppCompatActivity {
    boolean firstCheck = false;
    //expected permission
    public static final String GALLERY_PERMISSIONS = Manifest.permission.READ_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //initialize sugar orm
        SugarContext.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermission(GALLERY_PERMISSIONS);
            }
        }, 3000);
        //load license copy text
        if (MProfile.count(MProfile.class) > 0) {
            String name = (MProfile.findById(MProfile.class, 1)).getNames();
            ((TextView) findViewById(R.id.txt_license)).setText(String.valueOf("licensed to " + name.toLowerCase()));
        }
        //set toolbar for mobile
        Tools.setSystemBarColor(this, R.color.grey_900);
    }

    //launcher
    public void startMain() {
        //Think to start the new class
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //redirect to login if not signed in
                if (MProfile.count(MProfile.class) > 0) {
                    startActivity(new Intent(SplashScreen.this, Dashboard.class));
                    finish();
                } else {
                    //login details
                    startActivity(new Intent(SplashScreen.this, SignIn.class));
                    finish();
                }
            }
        }, 3000);
    }

    //Simple permission to check
    public void checkPermission(String permission) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            //Open permission screen
            firstCheck = true;
            startActivity(new Intent(SplashScreen.this, Permissions.class).putExtra("permission", GALLERY_PERMISSIONS));
        } else {
            startMain();
        }
    }

    //onResume activities
    @Override
    protected void onResume() {
        super.onResume();
        if (firstCheck)
            checkPermission(GALLERY_PERMISSIONS);
    }
}
