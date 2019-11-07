package com.rscbyte.homechurch.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.databinding.ActivityDashboardBinding;

public class Dashboard extends AppCompatActivity {

    //Main layout inflater holder
    ActivityDashboardBinding bdx = null;
    Activity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //assign context
        this.ctx = this;
        //inflate layout
        bdx = DataBindingUtil.setContentView(ctx, R.layout.activity_dashboard);
        //initialize toolbar
        initToolBar();
        //initialize components
        componentsInit();
    }

    //set header and toolbar
    public void initToolBar() {
        Tools.setSystemBarColor(ctx, R.color.app_color_1);
        Tools.setSystemBarLight(ctx);
    }

    //components initializer
    public void componentsInit() {
        //left btn initializer
        bdx.toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ctx, Profile.class));
            }
        });
    }
}
