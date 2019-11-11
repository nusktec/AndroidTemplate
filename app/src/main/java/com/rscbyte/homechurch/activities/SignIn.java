package com.rscbyte.homechurch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.Utils.Constants;
import com.rscbyte.homechurch.Utils.SharedPreferred;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.databinding.ActivitySignInBinding;
import com.rscbyte.homechurch.https.ApiController;
import com.rscbyte.homechurch.https.Listeners;
import com.rscbyte.homechurch.objects.OSignIn;

public class SignIn extends AppCompatActivity {

    ActivitySignInBinding bdx;
    Activity ctx;
    //class model
    OSignIn oSign = new OSignIn();
    SharedPreferred sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ctx = this;
        this.sp = new SharedPreferred(this);
        bdx = DataBindingUtil.setContentView(ctx, R.layout.activity_sign_in);
        bdx.setOsign(oSign);
        initToolBar();
        components();
    }

    //set header and toolbar
    @SuppressLint("SetTextI18n")
    public void initToolBar() {
        Tools.setSystemBarColor(ctx, R.color.grey_900);
    }

    //initializer
    void components() {
        //apply default
        oSign.setRemember(sp.getBoolean(Constants.LOGIN_CHK_VALUE));
        //sign up page
        bdx.btnCreateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open sign up page
                startActivity(new Intent(ctx, SignUp.class));
            }
        });

        //btn login
        bdx.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oSign.isReady(ctx, true)) {
                    //ready to sign in
                    oSign.setEnabled(false);
                    bdx.txtInfo.setText("Signing...");
                    requestUserAccount();
                }
            }
        });
    }

    //make a request to api server
    private void requestUserAccount() {
        ApiController apiController = new ApiController();
        apiController.userLogin(new Listeners.IAccount() {
            @Override
            public void isLogin(String udata, boolean isData) {
                if (isData) {
                    Tools.showToast(ctx, udata);
                    bdx.txtInfo.setText("Logged in...");
                    //open activities
                    startActivity(new Intent(ctx, Dashboard.class));
                    finish();
                } else {
                    //error
                    oSign.setEnabled(true);
                    bdx.txtInfo.setText("");
                }
            }

            @Override
            public void isRegistered(String udata) {

            }
        });
    }

}
