package com.rscbyte.homechurch.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {


    ActivitySignUpBinding bdx;
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
        Tools.setSystemBarColor(ctx, R.color.grey_900);
        //set onClose
        bdx.toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
