package com.rscbyte.homechurch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.databinding.ActivitySignInBinding;

public class SignUp extends AppCompatActivity {


    ActivitySignInBinding bdx;
    Activity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ctx = this;
        bdx = DataBindingUtil.setContentView(ctx, R.layout.activity_sign_up);
        initToolBar();
    }


    //set header and toolbar
    @SuppressLint("SetTextI18n")
    public void initToolBar() {
        Tools.setSystemBarLight(ctx);
        Tools.setSystemBarColor(ctx, R.color.app_color_1);

    }
}
