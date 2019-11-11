package com.rscbyte.homechurch.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.orm.SugarRecord;
import com.rscbyte.homechurch.R;
import com.rscbyte.homechurch.Utils.Tools;
import com.rscbyte.homechurch.databinding.ActivityProfileBinding;
import com.rscbyte.homechurch.models.MProfile;

public class Profile extends AppCompatActivity {

    //Main layout inflater holder
    ActivityProfileBinding bdx = null;
    Activity ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initialize context
        this.ctx = this;
        this.bdx = DataBindingUtil.setContentView(ctx, R.layout.activity_profile);
        //initialize toolbar
        initToolBar();
        //initialize components
        componentsInit();
        //data loader
        dataLoader();
    }

    //data loader
    public void dataLoader() {
        if (MProfile.count(MProfile.class) < 1) {
            Tools.showToast(ctx, "You have not setup user account");
            return;
        }

    }

    //set header and toolbar
    @SuppressLint("SetTextI18n")
    public void initToolBar() {
        Tools.setSystemBarColor(ctx, R.color.grey_900);
        bdx.toolbarTitle.setText("Profile Settings");
        //left btn initializer
        bdx.toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //components initializer
    public void componentsInit() {
        //save button
        bdx.btnSaveSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSettings();
            }
        });
      

    }

    //submit data
    public void saveSettings() {

    }
}
